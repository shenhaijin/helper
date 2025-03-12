package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.StrHelper;

public class KingBaseDataSource extends AbstractJdbcDataSource{
    public KingBaseDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setSchemaName(connectionConfig.getSchemaName());
        this.connConfig.setDriverClassName("com.kingbase8.Driver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:kingbase8://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername(),StrHelper.isBlank(connConfig.getSchemaName()) ? "": "&schema=" + connConfig.getSchemaName());
    }

    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:kingbase8://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
