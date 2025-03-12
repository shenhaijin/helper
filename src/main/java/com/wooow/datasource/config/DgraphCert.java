package com.wooow.datasource.config;

public class DgraphCert {
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 文件二进制Base64加密
     */
    private String data;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

}
