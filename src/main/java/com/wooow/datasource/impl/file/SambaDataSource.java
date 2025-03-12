package com.wooow.datasource.impl.file;


import com.hierynomus.smbj.SMBClient;
import com.hierynomus.smbj.auth.AuthenticationContext;
import com.hierynomus.smbj.connection.Connection;
import com.hierynomus.smbj.session.Session;
import com.hierynomus.smbj.share.DiskShare;
import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.SambaConfig;
import com.wooow.helper.StrHelper;
import jcifs.smb.SmbFile;

public class SambaDataSource extends AbstractDataSource<SambaConfig> {
    private String sambaVersion = null;
    public SambaDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new SambaConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
        this.connConfig.setUsername(connectionConfig.getUsername());
        this.connConfig.setPassword(connectionConfig.getPassword());
        this.connConfig.setDataBaseName(connConfig.getDataBaseName());
    }

    @Override
    public String getVersion(SambaConfig connConfig) throws Exception {
        return sambaVersion;
    }

    @Override
    public boolean connect(SambaConfig connConfig) throws Exception {
        boolean defaultResult = false;
        try {
            SmbFile smbFile =  new SmbFile("smb://" + connConfig.getUsername() + ":" + connConfig.getPassword() + "@" + connConfig.getIp() + ":" + connConfig.getPort() + "/" + connConfig.getDataBaseName() + "/");
            smbFile.connect();
            defaultResult = true;
            sambaVersion = "SMB1.0";
        }catch (Throwable ex){
            ex.getLocalizedMessage();
        }
        if(!defaultResult){
            SMBClient smbClient = null;
            try {
                smbClient = new SMBClient();
                Connection connection = smbClient.connect(connConfig.getIp(), Integer.parseInt(connConfig.getPort()));
                AuthenticationContext authenticationContext = new AuthenticationContext(connConfig.getUsername(), connConfig.getPassword().toCharArray(), null);
                Session session = connection.authenticate(authenticationContext);
                DiskShare diskShare = (DiskShare) session.connectShare(connConfig.getDataBaseName());
                defaultResult = true;
                sambaVersion = "SMB2.0/3.0";
            }catch (Throwable ex){
                ex.getLocalizedMessage();
            } finally {
                if(smbClient != null){
                    smbClient.close();
                }
            }
        }

        return defaultResult;
    }

    @Override
    public String getUrl(SambaConfig connConfig) throws Exception {
        return StrHelper.concat(true,"smb://", connConfig.getUsername(), "@",connConfig.getIp(),":", connConfig.getPort(),"/",connConfig.getDataBaseName(),"?username=",connConfig.getUsername());
    }
}
