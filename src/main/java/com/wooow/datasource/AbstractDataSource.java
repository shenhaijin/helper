package com.wooow.datasource;

public abstract class AbstractDataSource<C extends IConnConfig> implements IDataSource {
    protected C connConfig;

    public AbstractDataSource(ConnectionConfig connectionConfig) {
        // 将connectionConfig 转化程对应的IConnConfig对象
//        this.connConfig.setIp(connectionConfig.getIp());
//        this.connConfig.setPort(connectionConfig.getPort());
    }
    public boolean tryConnection() throws Exception{
        return connect(connConfig);
    }
    public String getVersion() throws Exception{
        return getVersion(connConfig);
    }
    public String getUrl() throws Exception{
        return getUrl(connConfig);
    }
    public abstract String getVersion(C connConfig) throws Exception;
    public abstract boolean connect(C connConfig) throws Exception;
    public abstract String getUrl(C connConfig) throws Exception;
}
