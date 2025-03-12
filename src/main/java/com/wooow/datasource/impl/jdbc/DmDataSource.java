package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.StrHelper;

public class DmDataSource extends AbstractJdbcDataSource {
    public DmDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("dm.jdbc.driver.DmDriver");
    }
    public String getUrl(JdbcConfig connConfig) throws Exception{
        return StrHelper.concat(true,"jdbc:dm://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }

    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception{
        return StrHelper.concat(true,"jdbc:dm://",connConfig.getIp(),":",connConfig.getPort());
    }


}
