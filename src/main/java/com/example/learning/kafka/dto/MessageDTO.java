package com.example.learning.kafka.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MessageDTO {
    private String topic;
    private String message;
}
