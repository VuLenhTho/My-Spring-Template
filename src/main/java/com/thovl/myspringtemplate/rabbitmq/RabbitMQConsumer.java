package com.thovl.myspringtemplate.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class RabbitMQConsumer {


    @RabbitListener(
            queues = {"TEST_1"},
            containerFactory = "TEST_1_CONSUMER"
    )
    public void consume(String message){
        log.info(String.format("Received message -> %s", message));

    }

}