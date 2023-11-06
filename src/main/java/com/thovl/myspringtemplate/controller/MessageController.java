package com.thovl.myspringtemplate.controller;

import com.thovl.myspringtemplate.dto.PublishDTO;
import com.thovl.myspringtemplate.service.RabbitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MessageController {
    private final RabbitService publisher;

    // http://localhost:8080/api/v1/publish?message=hello
    @PostMapping("/publish")
    public ResponseEntity<Boolean> sendMessage(@RequestBody PublishDTO message){
        boolean result = publisher.send(message.getMessage());
        return ResponseEntity.ok(result);
    }
}
