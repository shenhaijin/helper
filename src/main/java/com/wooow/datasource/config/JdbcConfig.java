package com.wooow.datasource.config;


import com.wooow.datasource.IConnConfig;

public class JdbcConfig extends IConnConfig {
    private String driverClassName;
    private String username;
    private String password;
    private String dataBaseName;
    private String schemaName;
    /**
     * 集群接连串
     */
    private String clusterUrl;
    public String getSchemaName() {
        return schemaName;
    }
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
    public String getDriverClassName() {
        return driverClassName;
    }
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
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
    public String getClusterUrl() {
        return clusterUrl;
    }
    public void setClusterUrl(String clusterUrl) {
        this.clusterUrl = clusterUrl;
    }
}
