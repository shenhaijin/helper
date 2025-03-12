package com.wooow.datasource.config;


import com.wooow.datasource.IConnConfig;

public class HdfsConfig extends IConnConfig {
    protected String username;
    protected String dataBaseName;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDataBaseName() {
        return dataBaseName;
    }
    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}
