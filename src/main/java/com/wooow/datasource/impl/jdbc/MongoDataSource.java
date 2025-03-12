package com.wooow.datasource.impl.jdbc;

import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;

public class MongoDataSource extends AbstractJdbcDataSource {
    public MongoDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);

        this.connConfig.setDriverClassName("mongodb.jdbc.MongoDriver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:mongo://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }
    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:mongo://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
}
