package com.wooow.datasource.impl.file;

import cn.hutool.core.util.StrUtil;

import com.wooow.datasource.config.FtpConfig;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpDataSource extends AbstractDataSource<FtpConfig> {
    public FtpDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new FtpConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setUsername(connectionConfig.getUsername());
        this.connConfig.setPassword(connectionConfig.getPassword());
    }

    @Override
    public String getVersion(FtpConfig connConfig) throws Exception {
        return "";
    }

    @Override
    public boolean connect(FtpConfig connConfig) throws Exception {
        boolean defaultResult = false;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.connect(connConfig.getIp(), Integer.parseInt(connConfig.getPort()));// 连接FTP服务器
            if(StrUtil.isAllNotBlank(connConfig.getUsername(), connConfig.getPassword())){
                // 设置被动模式
                ftpClient.enterLocalPassiveMode();
                if(ftpClient.login(connConfig.getUsername(), connConfig.getPassword())){
                    defaultResult = true;
                }
            }
        }catch (Throwable ex){
            ex.getLocalizedMessage();
        }finally {
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect();
            }
        }

        return defaultResult;
    }

    @Override
    public String getUrl(FtpConfig connConfig) throws Exception {
        return StrUtil.concat(true, connConfig.getIp(),":", connConfig.getPort(), "?username=",connConfig.getUsername());
    }
}
