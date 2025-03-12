package com.wooow.datasource.impl.file;

import cn.hutool.core.util.StrUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.SftpConfig;


import java.util.Properties;

public class SftpDataSource extends AbstractDataSource<SftpConfig> {
    public SftpDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new SftpConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setUsername(connectionConfig.getUsername());
        this.connConfig.setPassword(connectionConfig.getPassword());
    }

    @Override
    public String getVersion(SftpConfig connConfig) throws Exception {
        return "";
    }

    @Override
    public boolean connect(SftpConfig connConfig) throws Exception {
        boolean defaultResult = false;
        JSch jsch = new JSch(); // 创建JSch
        Session session = null;
        Channel channel = null;
        try {
            session =
                    jsch.getSession(connConfig.getUsername(),connConfig.getIp(),Integer.parseInt(connConfig.getPort()));
            if(StrUtil.isNotBlank(connConfig.getPassword())){
                session.setPassword(connConfig.getPassword());
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(5000); // 设置超时时间 5s
            session.connect(); // 通过Session建立链接
            channel = session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
            defaultResult = true;
        }catch (Throwable ex){
            ex.getLocalizedMessage();
        }finally {
            if(channel != null && channel.isConnected()){
                channel.disconnect();
            }
            if(session != null && session.isConnected()){
                session.disconnect();
            }
        }
        return defaultResult;
    }

    @Override
    public String getUrl(SftpConfig connConfig) throws Exception {
        return StrUtil.concat(true, connConfig.getIp(),":", connConfig.getPort(), "?username=",connConfig.getUsername());
    }
}
