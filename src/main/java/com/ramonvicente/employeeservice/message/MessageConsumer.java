package com.ramonvicente.employeeservice.message;

import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = "${employee.service.event.producer.topic}", 
                    groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message) {
        log.info(message);
    }

}
