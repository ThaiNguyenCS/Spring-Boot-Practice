package com.example.learning.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaEvent<T> {
    private String eventId = UUID.randomUUID().toString();
    private String eventType;
    private Integer eventVersion = 1;
    private Long timestamp = Instant.now().getEpochSecond();
    private String source;

    private T payload;
}
