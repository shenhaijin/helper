package com.wooow.datasource.impl;

import com.wooow.datasource.AbstractDataSource;
import com.wooow.datasource.ConnectionConfig;
import com.wooow.datasource.config.KafkaConfig;
import com.wooow.helper.IoHelper;
import com.wooow.helper.StrHelper;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;

import java.util.Properties;

public class KafkaDataSource extends AbstractDataSource<KafkaConfig> {
    public KafkaDataSource(ConnectionConfig connectionConfig) {
        super(connectionConfig);
        this.connConfig = new KafkaConfig();
        this.connConfig.setIp(connectionConfig.getIp());
        this.connConfig.setPort(connectionConfig.getPort());
    }

    @Override
    public String getVersion(KafkaConfig connConfig) throws Exception {
        return "";
    }

    @Override
    public boolean connect(KafkaConfig connConfig) throws Exception {
        boolean defaultResult = false;
        AdminClient kafkaClient = null;
        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", "http://" + connConfig.getIp() + ":" + connConfig.getPort());
            props.put("request.timeout.ms", "5000");  // 请求总超时时间设为 5 秒
            props.put("connections.max.idle.ms", "10000");  // 最长空闲连接时间
            kafkaClient = AdminClient.create(props);
            ListTopicsResult topicsResult = kafkaClient.listTopics(new ListTopicsOptions().timeoutMs(5000));
            topicsResult.names().get();
            defaultResult = true;
        } catch (Throwable ex) {
            ex.getLocalizedMessage();
        } finally {
            IoHelper.close(kafkaClient);
        }
        return defaultResult;
    }

    @Override
    public String getUrl(KafkaConfig connConfig) throws Exception {
        return StrHelper.concat(true,"http://",connConfig.getIp(),":",connConfig.getPort());
    }
}
