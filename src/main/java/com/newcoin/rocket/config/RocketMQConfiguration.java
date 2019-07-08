package com.newcoin.rocket.config;


import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @description: rocketMQ配置
 * @author: Yang
 * @create: 2019-06-25 22:51
 */
@Configuration
@ConditionalOnProperty(name = "rocket-mq.enabled", havingValue = "true")
public class RocketMQConfiguration {

    @Value("${rocket-mq.property.group-id}")
    public String groupId;

    @Value("${rocket-mq.property.access-key}")
    public String accessKey;

    @Value("${rocket-mq.property.secret-key}")
    public String secretKey;

    @Value("${rocket-mq.property.namesrv-addr}")
    public String namesrvAddr;

    @Value("${rocket-mq.property.message-model:CLUSTERING}")
    public String messageModel;

    @Value("${rocket-mq.property.max-reconsume-times:16}")
    public String maxReconsumeTimes;

    @Value("${rocket-mq.property.topic}")
    public String topic;

    @Value("${rocket-mq.property.tags}")
    public String tags;


    @Autowired
    private MessageListener messageListener;

    @Bean(
            name = "consumer",
            initMethod = "init",
            destroyMethod = "destroy"
    )
    public SpringConsumer consumer() {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, groupId);
        properties.setProperty(PropertyKeyConst.AccessKey, accessKey);
        properties.setProperty(PropertyKeyConst.SecretKey, secretKey);
        properties.setProperty(PropertyKeyConst.NAMESRV_ADDR, namesrvAddr);
        // 订阅方式 (默认集群)
        properties.put(PropertyKeyConst.MessageModel, messageModel);
        // 消息消费失败时的最大重试次数
        // 最大重试次数小于等于 16 次，则重试时间间隔同上表描述。
        // 最大重试次数大于 16 次，超过 16 次的重试时间间隔均为每次 2 小时。
        properties.put(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);

        return new SpringConsumer(properties, messageListener, topic, tags);
    }

}
