package com.wooow.datasource.config;

import com.wooow.datasource.IConnConfig;

public class OwnCloudConfig extends IConnConfig {
    protected String username;
    protected String password;
    protected String encryptionPassWord;

    public String getEncryptionPassWord() {
        return encryptionPassWord;
    }

    public void setEncryptionPassWord(String encryptionPassWord) {
        this.encryptionPassWord = encryptionPassWord;
    }

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
}
