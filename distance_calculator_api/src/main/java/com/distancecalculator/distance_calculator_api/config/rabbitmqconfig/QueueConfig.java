package com.distancecalculator.distance_calculator_api.config.rabbitmqconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    
    @Bean
    public Queue customerCepRequestQueue() {
        return new Queue("customercep.request.queue");
    }

    @Bean
    public Queue customerCepResponseQueue() {
        return new Queue("customercep.response.queue");
    }

    @Bean
    public Queue productCepRequestQueue() {
        return new Queue("productcep.request.queue");
    }

    @Bean
    public Queue productCepResponseQueue() {
        return new Queue("productcep.response.queue");
    }

    @Bean
    public Queue freightCalculated() {
        return new Queue("freight.calculated.queue");
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("distance.exchange");
    }



}
