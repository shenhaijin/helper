package com.wooow.datasource.config;


import com.wooow.datasource.IConnConfig;

import java.util.List;

public class DgraphConfig extends IConnConfig {
    private String mutationPort;
    public String getMutationPort() {
        return mutationPort;
    }
    public void setMutationPort(String mutationPort) {
        this.mutationPort = mutationPort;
    }
    private List<DgraphCert> certList;
    public List<DgraphCert> getCertList() {
        return certList;
    }
    public void setCertList(List<DgraphCert> certList) {
        this.certList = certList;
    }
}
