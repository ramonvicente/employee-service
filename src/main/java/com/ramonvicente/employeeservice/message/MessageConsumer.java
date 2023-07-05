package com.ramonvicente.employeeservice.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = "employee-topic", groupId = "employee-service-group-id")
    public void listen(String message) {
        log.info(message);
    }

}
