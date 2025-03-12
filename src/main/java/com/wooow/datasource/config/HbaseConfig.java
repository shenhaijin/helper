package com.wooow.datasource.config;


import com.wooow.datasource.IConnConfig;

public class HbaseConfig extends IConnConfig {
    protected String dataBaseName;
    protected String username;
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
