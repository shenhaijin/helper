package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.StrHelper;

public class PostGresDataSource extends AbstractJdbcDataSource {
    public PostGresDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("org.postgresql.Driver");
    }

    public String getUrl(JdbcConfig connConfig) throws Exception{
        return StrHelper.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername(),StrHelper.isBlank(connConfig.getSchemaName()) ? "" : "&schema=" + connConfig.getSchemaName());
    }
    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
