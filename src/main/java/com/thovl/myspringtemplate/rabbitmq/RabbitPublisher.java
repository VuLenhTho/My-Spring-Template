package com.thovl.myspringtemplate.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitPublisher.class);

    private final RabbitTemplate rabbitTemplate;


    public boolean sendMessage(String exchange, String routingKey, String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        } catch (Exception e){
            log.error("Send rabbit error", e);
            throw e;
        }

        return true;
    }
}
