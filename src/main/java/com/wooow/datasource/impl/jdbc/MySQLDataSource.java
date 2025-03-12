package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.StrHelper;

public class MySQLDataSource extends AbstractJdbcDataSource{
    public MySQLDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("com.mysql.jdbc.Driver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:mysql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName() ,"?username=",connConfig.getUsername());
    }
    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:mysql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
