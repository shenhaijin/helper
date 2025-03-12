package com.wooow.datasource.impl.jdbc;

import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;

public class PostGresDataSource extends AbstractJdbcDataSource {
    public PostGresDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("org.postgresql.Driver");
    }

    public String getUrl(JdbcConfig connConfig) throws Exception{
//        return StrUtil.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),":",connConfig.getDataBaseName());
        return StrUtil.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername(),StrUtil.isBlank(connConfig.getSchemaName()) ? "" : "&schema=" + connConfig.getSchemaName());
    }
    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:postgresql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
