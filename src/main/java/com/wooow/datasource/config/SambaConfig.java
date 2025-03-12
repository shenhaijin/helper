package com.wooow.datasource.config;

import com.wooow.datasource.IConnConfig;
public class SambaConfig extends IConnConfig {
    protected String username;
    protected String password;
    protected String dataBaseName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }
}
