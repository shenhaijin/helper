package com.wooow.datasource.config;


import com.wooow.datasource.IConnConfig;

public class EsConfig extends IConnConfig {
    protected String username;
    protected String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
