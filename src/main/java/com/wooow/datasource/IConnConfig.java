package com.wooow.datasource;

/**
 * 数据源连接信息
 */
public class IConnConfig {
    protected String ip;
    protected String port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
