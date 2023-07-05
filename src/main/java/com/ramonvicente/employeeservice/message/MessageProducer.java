package com.ramonvicente.employeeservice.message;

import org.springframework.kafka.core.KafkaTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
