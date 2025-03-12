package com.wooow.datasource.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.HdfsConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

public class HdfsDataSource extends AbstractDataSource<HdfsConfig> {
    public HdfsDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new HdfsConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setDataBaseName(connectionConfig.getDataBaseName());
        this.connConfig.setUsername(connectionConfig.getUsername());

    }
    @Override
    public String getVersion(HdfsConfig connConfig) throws Exception {
        return "";
    }
    @Override
    public boolean connect(HdfsConfig connConfig) throws Exception {
        boolean defaultResult = false;
        FileSystem fs = null;
        try {
            String hdfsUrl = StrUtil.concat(true,"hdfs://", connConfig.getIp(), connConfig.getPort());
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", hdfsUrl);
            fs = FileSystem.get(URI.create(hdfsUrl), conf, connConfig.getUsername());
            fs.getStatus();
            fs.listStatus(new Path(connConfig.getDataBaseName()));
            defaultResult = true;
        }catch (Throwable ex){
            ex.getLocalizedMessage();
        }finally {
            IoUtil.close(fs);
        }
        return defaultResult;
    }

    @Override
    public String getUrl(HdfsConfig connConfig) throws Exception {
        return StrUtil.concat(true,"hdfs://", connConfig.getIp(), ":",connConfig.getPort(),connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }
}
