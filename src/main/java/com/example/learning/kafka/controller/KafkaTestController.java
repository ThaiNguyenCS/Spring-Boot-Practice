package com.example.learning.kafka.controller;

import com.example.learning.kafka.dto.MessageDTO;
import com.example.learning.kafka.service.KafkaTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/kafka-test")
@RequiredArgsConstructor
public class KafkaTestController {
    private final KafkaTestService kafkaTestService;

    @PostMapping("/send-msg")
    public ResponseEntity<String> sendMsg(@RequestBody MessageDTO dto) throws ExecutionException, InterruptedException {
        kafkaTestService.sendMsg(dto);
        return ResponseEntity.ok("OK");
    }
}
