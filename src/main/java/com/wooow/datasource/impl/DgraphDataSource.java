package com.wooow.datasource.impl;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.DgraphCert;
import com.wooow.datasource.config.DgraphConfig;
import com.wooow.helper.CollHelper;
import com.wooow.helper.ObjectHelper;
import com.wooow.helper.StrHelper;
import io.dgraph.DgraphClient;
import io.dgraph.DgraphGrpc;
import io.grpc.*;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class DgraphDataSource extends AbstractDataSource<DgraphConfig> {
    private String version;
    public DgraphDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new DgraphConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setMutationPort(connectionConfig.getMutationPort());
        this.connConfig.setCertList(connectionConfig.getCertList());
    }

    @Override
    public String getVersion(DgraphConfig connConfig) throws Exception {
        if(StrHelper.isBlank(version)){
            connect(connConfig);
        }
        return version;
    }

    @Override
    public boolean connect(DgraphConfig connConfig) throws Exception {
        boolean defaultResult = false;
        ManagedChannel channel = null;
        if(CollHelper.isNotEmpty(connConfig.getCertList())){
            InputStream rootCertInputStream = null;
            InputStream userCertInputStream = null;
            InputStream userCertKeyInputStream = null;
            for(DgraphCert cert : connConfig.getCertList()){
                String base64Data = cert.getData();  // 获取经Base64加密的二进制字符串
                byte[] fileBytes = Base64.getDecoder().decode(base64Data);  // 使用 Hutool 解码字符串为字节流
                String certType = cert.getType();
                if (StrHelper.equalsIgnoreCase(certType, "root")) {
                    rootCertInputStream = new ByteArrayInputStream(fileBytes);
                } else if (StrHelper.equalsIgnoreCase(certType, "user")) {
                    userCertInputStream = new ByteArrayInputStream(fileBytes);
                } else if (StrHelper.equalsIgnoreCase(certType, "key")) {
                    userCertKeyInputStream = new ByteArrayInputStream(fileBytes);
                }
            }
            if(ObjectHelper.isNull(rootCertInputStream) || ObjectHelper.isNull(userCertInputStream) || ObjectHelper.isNull(userCertKeyInputStream)){
                return defaultResult;
            }
            SslContextBuilder builder = GrpcSslContexts.forClient();
            builder.trustManager(rootCertInputStream);
            builder.keyManager(userCertInputStream,userCertKeyInputStream);
            SslContext sslContext = builder.build();
            channel = NettyChannelBuilder.forAddress(connConfig.getIp(), Integer.parseInt(connConfig.getMutationPort()))
                    .sslContext(sslContext)
                    .build();
        }else{
            channel = ManagedChannelBuilder.forAddress(connConfig.getIp(), Integer.parseInt(connConfig.getMutationPort()))
                    .usePlaintext()
                    .build();
            HttpRequest httpRequest = HttpUtil.createPost("http://" + connConfig.getIp() + ":" + connConfig.getPort());
            httpRequest.body("schema {}", ContentType.FORM_URLENCODED.getValue());
            HttpResponse response = httpRequest.execute();
            if(ObjectHelper.isNull(response) || response.getStatus() != 200){
                return defaultResult;
            }
        }
        DgraphGrpc.DgraphStub stub = DgraphGrpc.newStub(channel);
        // 设置超时时间，此时间会应用至服务端，避免客户端超时后服务端仍在查询的资源浪费
        ClientInterceptor timeoutInterceptor = new ClientInterceptor() {
            @Override
            public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
                    MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, io.grpc.Channel next) {
                return next.newCall(method, callOptions.withDeadlineAfter(5000, TimeUnit.MILLISECONDS));
            }
        };
        stub = stub.withInterceptors(timeoutInterceptor);
        DgraphClient dgraphClient = new DgraphClient(stub);
        defaultResult = true;
        version = dgraphClient.checkVersion().getTag();
        return defaultResult;
    }

    @Override
    public String getUrl(DgraphConfig connConfig) throws Exception {
        return StrHelper.concat(true,"http://",connConfig.getIp(),":",connConfig.getPort());
    }
}
