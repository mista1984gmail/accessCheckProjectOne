package org.example.access.check.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessCheckConfig {
    @Value("${rabbitmq.exchanges.access-check}")
    private String accessCheckExchange;

    @Value("${rabbitmq.queues.access-check}")
    private String accessCheckQueue;

    @Value("${rabbitmq.routing-keys.access-check}")
    private String accessCheckRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange(){
        return new TopicExchange(this.accessCheckExchange);
    }
    @Bean
    public Queue notificationQueue(){
        return new Queue(this.accessCheckQueue);
    }

    @Bean
    public Binding internalToNotificationBinding(){
        return BindingBuilder
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with(this.accessCheckRoutingKey);
    }


    public String getAccessCheckExchange() {
        return accessCheckExchange;
    }

    public String getAccessCheckQueue() {
        return accessCheckQueue;
    }

    public String getAccessCheckRoutingKey() {
        return accessCheckRoutingKey;
    }
}
