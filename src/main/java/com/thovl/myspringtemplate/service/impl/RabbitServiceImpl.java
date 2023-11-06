package com.thovl.myspringtemplate.service.impl;

import com.thovl.myspringtemplate.rabbitmq.RabbitPublisher;
import com.thovl.myspringtemplate.service.RabbitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitServiceImpl implements RabbitService {
    private final RabbitPublisher publisher;

    @Override
    public boolean send(String message) {
        log.info("Send rabbit message {}", message);
        return publisher.sendMessage("", "TEST_1", message);
    }
}
