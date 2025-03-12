package com.wooow.datasource;

/**
 * 数据源连接信息
 */
public class IConnConfig {
    protected String ip;
    protected String port;
//    protected String version;
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }

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
