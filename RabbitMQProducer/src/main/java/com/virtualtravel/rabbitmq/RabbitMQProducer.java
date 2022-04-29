package com.virtualtravel.rabbitmq;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {
    private final AmqpTemplate amqpTemplate;

    public void send(Object payload, String exchange, String key){
        amqpTemplate.convertAndSend(exchange, key, payload);
    }
}
