package com.wooow.datasource.impl.jdbc;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;

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
        if(StrUtil.isBlank(dbVersion)){
            Connection connection = null;
            try {
                String jdbcUrl = connConfig.getClusterUrl();
                if(StrUtil.isBlank(jdbcUrl)){
                    jdbcUrl = getJdbcUrl(connConfig);
                }
                connection = DriverManager.getConnection(jdbcUrl, connConfig.getUsername(), connConfig.getPassword());
                DatabaseMetaData metaData = connection.getMetaData();
                dbVersion = metaData.getDatabaseProductVersion();
            }catch (Throwable ex){
                ex.getLocalizedMessage();
            }finally {
                IoUtil.close(connection);
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
            if(StrUtil.isBlank(jdbcUrl)){
                jdbcUrl = getJdbcUrl(connConfig);
            }
            connection = DriverManager.getConnection(jdbcUrl, connConfig.getUsername(), connConfig.getPassword());
            DatabaseMetaData metaData = connection.getMetaData();
            dbVersion = metaData.getDatabaseProductVersion();
            defaultResult = true;
        }catch (Throwable ex){
            ex.getLocalizedMessage();
        }finally {
            IoUtil.close(connection);
        }
        return defaultResult;
    }

    protected abstract String getJdbcUrl(JdbcConfig config) throws Exception;
}
