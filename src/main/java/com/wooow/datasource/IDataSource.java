package com.wooow.datasource;

public interface IDataSource {
    /**
     * 数据源测试连接
     * @return
     * @throws Exception
     */
    boolean tryConnection() throws Exception;

    /**
     * 数据源版本
     * @return
     * @throws Exception
     */
    String getVersion() throws Exception;

    /**
     * 数据源拼接url地址
     * @return
     * @throws Exception
     */
    String getUrl() throws Exception;

}
