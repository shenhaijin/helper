package com.wooow.datasource.impl.jdbc;

import cn.hutool.core.util.StrUtil;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.JdbcConfig;

import java.util.List;


public class MySQLDataSource extends AbstractJdbcDataSource{
    public MySQLDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig.setDriverClassName("com.mysql.jdbc.Driver");
    }

    @Override
    public String getUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:mysql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName() ,"?username=",connConfig.getUsername());
    }
    protected String getJdbcUrl(JdbcConfig connConfig) throws Exception {
        return StrUtil.concat(true,"jdbc:mysql://",connConfig.getIp(),":",connConfig.getPort(),"/",connConfig.getDataBaseName());
    }
//    public List<JdbcDataSet> listDataSet(String dataType,String schemaName) throws Exception{
//        ConnectionInfo connectionInfo = GeoStarCoreHelper.createConnectionInfo(this.connConfig);
//        String address = "";
//        connectionInfo.setProtocol(ConnectionProtocol.MYSQL);
//        connectionInfo.getProperties().put("PreConnectionNum", "2");
//        connectionInfo.setPoolName(address + "_" + this.connConfig.getUsername());
//        connectionInfo.setUrlPrefix(getJdbcUrl(this.connConfig));
//        connectionInfo.setUsername(this.connConfig.getUsername());
//        connectionInfo.setAddress(address);
//        connectionInfo.setPassword(this.connConfig.getPassword());
//
//
//        IDataBase dataBase =
//                DataBaseManager.getInstance().getDataBaseInstance(connectionInfo);
//        List<DataSourceInfo> result = dataBase.queryDataSourceInfo(DataType.getDataTypeInstance(dataType),"");
//        return null;
//    }
}
