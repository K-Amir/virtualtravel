package com.virtualtravel.empresa.BookingListener;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingListenerConfig {
    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange("booking-exchange");
    }
    @Bean
    public Queue notificationQueue(){
        return new Queue("booking");
    }

    @Bean
    public Binding internalToNotificationBinding(){
        return BindingBuilder.bind(notificationQueue())
                .to(internalTopicExchange())
                .with("booking.com");

    }
}
