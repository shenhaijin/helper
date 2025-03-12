package com.wooow.datasource;


import com.wooow.datasource.config.DgraphCert;

import java.util.List;

/**
 * 数据源连接信息
 * 所有数据源连接信息
 */
public class ConnectionConfig {
    private String type;
    private String ip;
    private String port;
    private String username;
    private String password;
    private String dataBaseName;
    private String schemaName;
    private String version;
    private String encryptionPassWord;
    private String clusterUrl;
    private List<DgraphCert> certList;

    private String mutationPort;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEncryptionPassWord() {
        return encryptionPassWord;
    }

    public void setEncryptionPassWord(String encryptionPassWord) {
        this.encryptionPassWord = encryptionPassWord;
    }

    public String getClusterUrl() {
        return clusterUrl;
    }

    public List<DgraphCert> getCertList() {
        return certList;
    }

    public void setCertList(List<DgraphCert> certList) {
        this.certList = certList;
    }

    public void setClusterUrl(String clusterUrl) {
        this.clusterUrl = clusterUrl;
    }

    public String getMutationPort() {
        return mutationPort;
    }

    public void setMutationPort(String mutationPort) {
        this.mutationPort = mutationPort;
    }

    public static class Builder{
        private String type;
        private String ip;
        private String port;
        private String username;
        private String password;
        private String dataBaseName;
        private String schemaName;
        private String version;
        private String encryptionPassWord;
        private String clusterUrl;

        private List<DgraphCert> certList;

        private String mutationPort;

        public Builder type(String type){
            this.type = type;
            return this;
        }
        public Builder ip(String ip){
            this.ip = ip;
            return this;
        }

        public Builder port(String port){
            this.port = port;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder dataBaseName(String dataBaseName){
            this.dataBaseName = dataBaseName;
            return this;
        }
        public Builder schemaName(String schemaName){
            this.schemaName = schemaName;
            return this;
        }
        public Builder version(String version){
            this.version = version;
            return this;
        }
        public Builder encryptionPassWord(String encryptionPassWord){
            this.encryptionPassWord = encryptionPassWord;
            return this;
        }

        public Builder clusterUrl(String clusterUrl){
            this.clusterUrl = clusterUrl;
            return this;
        }
        public Builder certList(List<DgraphCert> certList){
            this.certList = certList;
            return this;
        }

        public Builder mutationPort(String mutationPort){
            this.mutationPort = mutationPort;
            return this;
        }
        public ConnectionConfig build(){
            ConnectionConfig connectionConfig = new ConnectionConfig();
            connectionConfig.setIp(this.ip);
            connectionConfig.setPort(this.port);
            connectionConfig.setUsername(this.username);
            connectionConfig.setPassword(this.password);
            connectionConfig.setDataBaseName(this.dataBaseName);
            connectionConfig.setSchemaName(this.schemaName);
            connectionConfig.setVersion(this.version);
            connectionConfig.setType(this.type);
            connectionConfig.setEncryptionPassWord(this.encryptionPassWord);
            connectionConfig.setClusterUrl(this.clusterUrl);
            connectionConfig.setCertList(this.certList);
            connectionConfig.setMutationPort(this.mutationPort);
            return connectionConfig;
        }
    }
}
