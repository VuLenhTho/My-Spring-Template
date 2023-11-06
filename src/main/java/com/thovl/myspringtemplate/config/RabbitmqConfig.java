package com.thovl.myspringtemplate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class RabbitmqConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("localhost:5669");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
//        connectionFactory.setConnectionCacheSize(3);
        connectionFactory.setConnectionLimit(3);
        connectionFactory.setChannelCacheSize(500);
        connectionFactory.setChannelCheckoutTimeout(5000);




        connectionFactory.setExecutor(Executors.newCachedThreadPool());

        return connectionFactory;
    }

    @Bean("TEST_1_CONSUMER")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(CachingConnectionFactory cachingConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(cachingConnectionFactory.getPublisherConnectionFactory());
        factory.setConcurrentConsumers(5);
        factory.setMaxConcurrentConsumers(5);
        factory.setStartConsumerMinInterval(1000l);
        return factory;
    }

}
