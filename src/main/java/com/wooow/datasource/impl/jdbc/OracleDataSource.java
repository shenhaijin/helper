package com.wooow.datasource.impl.jdbc;

import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;
import com.wooow.helper.StrHelper;

public class OracleDataSource extends AbstractJdbcDataSource {
    public OracleDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    }
    public String getUrl(JdbcConfig connConfig) throws Exception{
        return StrHelper.concat(true,"jdbc:oracle:thin:@",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }

    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrHelper.concat(true,"jdbc:oracle:thin:@",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }

}
