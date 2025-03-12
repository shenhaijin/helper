package com.wooow.datasource.impl.file;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wooow.datasource.config.OwnCloudConfig;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class OwnCloudDataSource extends AbstractDataSource<OwnCloudConfig> {
    public OwnCloudDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new OwnCloudConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setUsername(connectionConfig.getUsername());
        this.connConfig.setPassword(connectionConfig.getPassword());
        this.connConfig.setEncryptionPassWord(connectionConfig.getEncryptionPassWord());
    }

    @Override
    public String getVersion(OwnCloudConfig connConfig) throws Exception {
        return "";
    }

    @Override
    public boolean connect(OwnCloudConfig connConfig) throws Exception {
        boolean defaultResult = false;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://" + connConfig.getIp() + ":" + connConfig.getPort() + "/owncloud/index.php/apps/files/ajax/list.php");
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("username", connConfig.getUsername()));
        formparams.add(new BasicNameValuePair("password", connConfig.getEncryptionPassWord()));
        formparams.add(new BasicNameValuePair("methed", "login"));
        formparams.add(new BasicNameValuePair("dir", "/"));
        UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        httppost.setEntity(uefEntity);
        CloseableHttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String data = EntityUtils.toString(entity, "UTF-8").toString();
            if (StrUtil.isNotBlank(data)) {
                JSONObject jsonObject = JSONUtil.parseObj(data);
                String status = jsonObject.getStr("status");
                if(!StrUtil.equalsIgnoreCase(status,"error")){
                    defaultResult = true;
                }
            } else { // 适配V5.2及V6.0升级后，关于网盘连接及获取数据列表功能的适配
                formparams.set(1, new BasicNameValuePair("password", URLEncoder.encode(connConfig.getEncryptionPassWord(), "utf-8")));
                UrlEncodedFormEntity uefEntityfor60 = new UrlEncodedFormEntity(formparams, "UTF-8");
                httppost.setEntity(uefEntityfor60);
                response = httpclient.execute(httppost);
                HttpEntity entityfor60 = response.getEntity();
                if (entityfor60 != null) {
                    String otherData = EntityUtils.toString(entityfor60, "UTF-8").toString();
                    if (StrUtil.isNotBlank(otherData)) {
                        defaultResult = true;
                    } else {
                        // 当此环境网盘对应的用户系统和cas暂未升级至v6.2及更高版本时，直接将网盘密码不做加密处理传输
                        formparams.set(1, new BasicNameValuePair("password", URLEncoder.encode(connConfig.getPassword(), "utf-8")));
                        UrlEncodedFormEntity uefEntityfor60other = new UrlEncodedFormEntity(formparams, "UTF-8");
                        httppost.setEntity(uefEntityfor60other);
                        response = httpclient.execute(httppost);
                        HttpEntity otherEntityfor60 = response.getEntity();
                        if (otherEntityfor60 != null) {
                            otherData =  EntityUtils.toString(otherEntityfor60, "UTF-8").toString();
                            if (StrUtil.isNotBlank(otherData)) {
                                defaultResult = true;
                            }
                        }
                    }
                }

            }
        }
        return defaultResult;
    }

    @Override
    public String getUrl(OwnCloudConfig connConfig) throws Exception {
        return StrUtil.concat(true, "http://",connConfig.getIp(),":", connConfig.getPort(), "/owncloud?username=",connConfig.getUsername());
    }
}
