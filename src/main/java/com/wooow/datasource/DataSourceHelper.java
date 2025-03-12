package com.wooow.datasource;


import com.wooow.datasource.impl.file.FtpDataSource;
import com.wooow.datasource.impl.file.SambaDataSource;
import com.wooow.datasource.impl.file.SftpDataSource;
import com.wooow.datasource.impl.file.OwnCloudDataSource;
import com.wooow.datasource.impl.EsDataSource;
import com.wooow.datasource.impl.HbaseDataSource;
import com.wooow.datasource.impl.DgraphDataSource;
import com.wooow.datasource.impl.HdfsDataSource;
import com.wooow.datasource.impl.KafkaDataSource;
import com.wooow.datasource.impl.jdbc.*;
import com.wooow.helper.MapHelper;
import com.wooow.helper.ObjectHelper;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class DataSourceHelper {
    private static Map<String,Class<? extends IDataSource>> CLASS_MAP = new HashMap<String,Class<? extends IDataSource>>();
    static {
        MapHelper.clear(CLASS_MAP);
        CLASS_MAP.put("mysql", MySQLDataSource.class);
        CLASS_MAP.put("oracle", OracleDataSource.class);
        CLASS_MAP.put("postgresql", PostGresDataSource.class);
        CLASS_MAP.put("dm", DmDataSource.class);
        CLASS_MAP.put("mongodb", MongoDataSource.class);
        CLASS_MAP.put("sqlserver", SqlServerDataSource.class);
        CLASS_MAP.put("gbase", GbaseDataSource.class);
        CLASS_MAP.put("highgo", HigoDataSource.class);
        CLASS_MAP.put("kingbase",KingBaseDataSource.class);

        CLASS_MAP.put("sftp", SftpDataSource.class);
        CLASS_MAP.put("ftp", FtpDataSource.class);
        CLASS_MAP.put("samba", SambaDataSource.class);
        CLASS_MAP.put("owncloud", OwnCloudDataSource.class);

        CLASS_MAP.put("es",EsDataSource.class);
        CLASS_MAP.put("dgraph",DgraphDataSource.class);
        CLASS_MAP.put("kafka",KafkaDataSource.class);
        CLASS_MAP.put("hbase",HbaseDataSource.class);
        CLASS_MAP.put("hdfs",HdfsDataSource.class);
    }

    public static void init(Map<String,Class<? extends IDataSource>> clzMap){
        if(MapHelper.isNotEmpty(clzMap)){
            MapHelper.clear(CLASS_MAP);
            CLASS_MAP = clzMap;
        }
    }
    public static IDataSource createInstance(ConnectionConfig connectionConfig) throws Exception {
        IDataSource dataSource = null;
        String type = connectionConfig.getType();
        Class<? extends IDataSource> clazz = CLASS_MAP.get(type);
        if(ObjectHelper.isNotNull(clazz)){
            Constructor<?>  constructor = clazz.getDeclaredConstructor(ConnectionConfig.class);
            dataSource = (IDataSource) constructor.newInstance(connectionConfig);
        }
        return dataSource;
    }

    public static void main(String[] args) throws Exception {
        testPostgres();
        testSftp();
        testOwnCloud();
    }
    private static void testPostgres() throws Exception{
        ConnectionConfig.Builder builder = new ConnectionConfig.Builder();
        ConnectionConfig connectionConfig =
                builder.type("postgres").ip("192.168.30.120").username("postgres").password("GeoStar@123").dataBaseName("geosmarter").schemaName("public").port("9042").build();
        IDataSource dataSource = DataSourceHelper.createInstance(connectionConfig);
        System.out.println("数据连接地址：" + dataSource.getUrl());
        System.out.println("测试连接结果：" + dataSource.tryConnection());
        System.out.println("数据库版本：" + dataSource.getVersion());
    }
    private static void testSftp() throws Exception {
        ConnectionConfig.Builder builder = new ConnectionConfig.Builder();
        ConnectionConfig connectionConfig =
                builder.type("sftp").ip("192.168.102.211").username("root").password("123@abc.com").dataBaseName("geosmarter").schemaName("public").port("22").build();
        IDataSource dataSource = DataSourceHelper.createInstance(connectionConfig);
        System.out.println("数据连接地址：" + dataSource.getUrl());
        System.out.println("测试连接结果：" + dataSource.tryConnection());
        System.out.println("数据库版本：" + dataSource.getVersion());
    }
    private static void testOwnCloud() throws Exception {
        ConnectionConfig.Builder builder = new ConnectionConfig.Builder();
        ConnectionConfig connectionConfig =
                builder.type("owncloud").ip("192.168.30.121").username("admin").password("123@Abc.com").dataBaseName("geosmarter").schemaName("public").port("82").build();
        IDataSource dataSource = DataSourceHelper.createInstance(connectionConfig);
        System.out.println("数据连接地址：" + dataSource.getUrl());
        System.out.println("测试连接结果：" + dataSource.tryConnection());
        System.out.println("数据库版本：" + dataSource.getVersion());
    }
}
