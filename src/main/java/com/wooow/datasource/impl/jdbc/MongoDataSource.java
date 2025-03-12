package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.StrHelper;

public class MongoDataSource extends AbstractJdbcDataSource {
    public MongoDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);

        this.connConfig.setDriverClassName("mongodb.jdbc.MongoDriver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:mongo://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }
    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:mongo://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
