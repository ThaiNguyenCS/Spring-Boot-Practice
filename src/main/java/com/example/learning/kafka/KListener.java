package com.example.learning.kafka;

import com.example.learning.kafka.dto.KafkaEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KListener {
    @KafkaListener(topics = "test-topic")
    public void handle(
            KafkaEvent<?> data,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partitionId,
            @Header(KafkaHeaders.OFFSET) long offset,
            @Header(KafkaHeaders.GROUP_ID) String groupId,
            Acknowledgment acknowledgment
    ) {
        Thread thread = Thread.currentThread();
        log.info("Received message: {}, key: {}, partitionId: {}, offset: {}, groupId: {} on thread {}",
                data.getPayload(), key, partitionId, offset, groupId, thread.getName());
        acknowledgment.acknowledge();
    }

//    @KafkaListener(topics = "test-topic", groupId = "group_name_2")
//    public void handle2(
//            KafkaEvent<?> data,
//            @Header(KafkaHeaders.RECEIVED_KEY) String key,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partitionId,
//            @Header(KafkaHeaders.OFFSET) long offset,
//            Acknowledgment acknowledgment
//    ) {
//        log.info("Received message 2: {}, key: {}, partitionId: {}, offset: {}",
//                data.getPayload(), key, partitionId, offset);
//        acknowledgment.acknowledge();
//    }
}
