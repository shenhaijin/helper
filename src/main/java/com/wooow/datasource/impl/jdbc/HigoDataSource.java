package com.wooow.datasource.impl.jdbc;

import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;

public class HigoDataSource extends AbstractJdbcDataSource{
    public HigoDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setSchemaName(connectionConfig.getSchemaName());
        this.connConfig.setDriverClassName("org.postgresql.Driver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername(),StrUtil.isBlank(connConfig.getSchemaName()) ? "" : "&schema="+connConfig.getSchemaName());
    }

    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception{
        return StrUtil.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
