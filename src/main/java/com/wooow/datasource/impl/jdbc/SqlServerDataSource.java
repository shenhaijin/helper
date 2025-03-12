package com.wooow.datasource.impl.jdbc;


import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;


public class SqlServerDataSource extends AbstractJdbcDataSource {
    public SqlServerDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:sqlserver://",connConfig.getIp(),":",connConfig.getPort(),";DatabaseName=",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }

    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:sqlserver://",connConfig.getIp(),":",connConfig.getPort(),";DatabaseName=",connConfig.getDataBaseName());
    }
}
