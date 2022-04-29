package com.virtualtravel.web.BusListener;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusListenerConfig {
    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange("bus-sync");
    }
    @Bean
    public Queue notificationQueue(){
        return new Queue("synchronize");
    }

    @Bean
    public Binding internalToNotificationBinding(){
        return BindingBuilder.bind(notificationQueue())
                .to(internalTopicExchange())
                .with("bus.sync");

    }
}
