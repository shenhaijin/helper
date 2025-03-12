package com.wooow.datasource.impl.jdbc;

import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;

public class DmDataSource extends AbstractJdbcDataSource {
    public DmDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("dm.jdbc.driver.DmDriver");
    }
    public String getUrl(JdbcConfig connConfig) throws Exception{
        return StrUtil.concat(true,"jdbc:dm://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }

    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception{
        return StrUtil.concat(true,"jdbc:dm://",connConfig.getIp(),":",connConfig.getPort());
    }


}
