package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.StrHelper;

public class HigoDataSource extends AbstractJdbcDataSource{
    public HigoDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setSchemaName(connectionConfig.getSchemaName());
        this.connConfig.setDriverClassName("org.postgresql.Driver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername(),StrHelper.isBlank(connConfig.getSchemaName()) ? "" : "&schema="+connConfig.getSchemaName());
    }

    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception{
        return StrHelper.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
