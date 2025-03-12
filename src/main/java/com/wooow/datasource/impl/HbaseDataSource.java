package com.wooow.datasource.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.HbaseConfig;

public class HbaseDataSource extends AbstractDataSource<HbaseConfig> {
    public HbaseDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new HbaseConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
    }

    @Override
    public String getVersion(HbaseConfig connConfig) throws Exception {
        return "";
    }

    @Override
    public boolean connect(HbaseConfig connConfig) throws Exception {
        boolean defaultResult = false;
        String requestUrl = StrUtil.concat(true,"http://", connConfig.getIp(),":", connConfig.getPort(), "/cluster/getInfo");
        String responseGet = HttpUtil.get(requestUrl);
        if (StrUtil.isNotBlank(responseGet) && responseGet.contains(",") && !"".equalsIgnoreCase(responseGet.split(",")[0])) {
            defaultResult = true;
        }
        return defaultResult;
    }

    @Override
    public String getUrl(HbaseConfig connConfig) throws Exception {
        return StrUtil.concat(true,connConfig.getIp(),":", connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }
}
