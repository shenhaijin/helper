package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.IoHelper;
import com.wooow.helper.StrHelper;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public abstract class AbstractJdbcDataSource extends AbstractDataSource<JdbcConfig> {
    protected String dbVersion;
    public AbstractJdbcDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new JdbcConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setDataBaseName(connectionConfig.getDataBaseName());
        this.connConfig.setUsername(connectionConfig.getUsername());
        this.connConfig.setPassword(connectionConfig.getPassword());
        this.connConfig.setSchemaName(connectionConfig.getSchemaName());
        this.connConfig.setClusterUrl(connectionConfig.getClusterUrl());
    }
    @Override
    public String getVersion(JdbcConfig connConfig) throws Exception {
        if(StrHelper.isBlank(dbVersion)){
            Connection connection = null;
            try {
                String jdbcUrl = connConfig.getClusterUrl();
                if(StrHelper.isBlank(jdbcUrl)){
                    jdbcUrl = getJdbcUrl(connConfig);
                }
                connection = DriverManager.getConnection(jdbcUrl, connConfig.getUsername(), connConfig.getPassword());
                DatabaseMetaData metaData = connection.getMetaData();
                dbVersion = metaData.getDatabaseProductVersion();
            }catch (Throwable ex){
                ex.getLocalizedMessage();
            }finally {
                IoHelper.close(connection);
            }
        }
        return dbVersion;
    }

    @Override
    public boolean connect(JdbcConfig connConfig) throws Exception {
        boolean defaultResult = false;
        Connection connection = null;
        try {
            String jdbcUrl = connConfig.getClusterUrl();
            if(StrHelper.isBlank(jdbcUrl)){
                jdbcUrl = getJdbcUrl(connConfig);
            }
            connection = DriverManager.getConnection(jdbcUrl, connConfig.getUsername(), connConfig.getPassword());
            DatabaseMetaData metaData = connection.getMetaData();
            dbVersion = metaData.getDatabaseProductVersion();
            defaultResult = true;
        }catch (Throwable ex){
            ex.getLocalizedMessage();
        }finally {
            IoHelper.close(connection);
        }
        return defaultResult;
    }

    protected abstract String getJdbcUrl(JdbcConfig config) throws Exception;
}
