package com.wooow.datasource.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.EsConfig;
import com.wooow.helper.StrHelper;

public class EsDataSource extends AbstractDataSource<EsConfig> {
    public EsDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new EsConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setUsername(connectionConfig.getUsername());
        this.connConfig.setPassword(connectionConfig.getPassword());
    }

    @Override
    public String getVersion(EsConfig connConfig) throws Exception {
        // 发送带有基本认证的 GET 请求
        String response = HttpRequest.get(StrHelper.concat(true,"http://",connConfig.getIp(),":",connConfig.getPort()))
                .basicAuth(connConfig.getUsername(), connConfig.getPassword())  // 设置基本认证的用户名和密码
                .timeout(5000)  // 设置连接超时时间，单位毫秒
                .execute()
                .body();  // 获取响应体
        JSONObject responseJson = JSONUtil.parseObj(response);
        return responseJson.getJSONObject("version").getStr("number");
    }

    @Override
    public boolean connect(EsConfig connConfig) throws Exception {
        boolean defaultResult = false;
        try {
//            EsClient esClient = new EsClient.EsClientBuilder()
//                    .setClusterNodes(new String[]{connConfig.getIp()+":"+connConfig.getPort()})
//                    .setUsername(connConfig.getUsername())
//                    .setPassword(connConfig.getPassword())
//                    .builder();
//            JsonObject jsonObject = esClient.getLowLevelClient().doHealthCheck();
//            if ("true".equals(jsonObject.get("isSuccess").getAsString())) {
//                defaultResult = true;
//            }
        }catch (Throwable ex){
            ex.getLocalizedMessage();
        }
        return defaultResult;
    }

    @Override
    public String getUrl(EsConfig connConfig) throws Exception {
        return StrHelper.concat(true,"http://",connConfig.getIp(),":",connConfig.getPort(),"?username=",connConfig.getUsername());
    }
}
