package com.newcoin.rocket.config;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.ONSFactory;
import lombok.extern.slf4j.Slf4j;


import java.util.Properties;

/**
 * @description:
 * @author: Yang
 * @create: 2019-06-26 12:33
 */
@Slf4j
public class SpringConsumer {

    private Properties consumerProperties;
    private MessageListener messageListener;
    private Consumer consumer;
    private String topic;
    private String tags;

    SpringConsumer(Properties _properties, MessageListener _messageListener, String _topic, String _tags) {
        consumerProperties = _properties;
        messageListener = _messageListener;
        topic = _topic;
        tags = _tags;
    }

    public void init() {
        consumer = ONSFactory.createConsumer(consumerProperties);
        consumer.subscribe(topic, tags, messageListener);
        // 消费者对象在使用之前必须要调用 start 初始化
        consumer.start();
        log.info("consumer start success.");
    }

    public void destroy() {
        log.info("consumer shutdown start");
        consumer.shutdown();
        log.info("consumer shutdown end");
    }
}
