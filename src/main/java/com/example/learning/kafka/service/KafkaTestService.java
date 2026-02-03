package com.example.learning.kafka.service;

import com.example.learning.kafka.dto.KafkaEvent;
import com.example.learning.kafka.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaTestService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public void sendMsg(MessageDTO messageDTO) throws ExecutionException, InterruptedException {
        Message<KafkaEvent<String>> msg = MessageBuilder
                .withPayload(KafkaEvent.<String>builder().payload(messageDTO.getMessage()).build())
                .setHeader(KafkaHeaders.TOPIC, messageDTO.getTopic())
                .setHeader(KafkaHeaders.KEY, UUID.randomUUID().toString())
                .build();
        kafkaTemplate.send(msg).whenComplete((r, ex) -> {
            if (ex != null) {
                log.error("Send failed", ex);
            } else {
                log.info(
                        "Sent to topic={}, partition={}, offset={}",
                        r.getRecordMetadata().topic(),
                        r.getRecordMetadata().partition(),
                        r.getRecordMetadata().offset()
                );
            }
        });
    }
}
