package com.distancecalculator.distance_calculator_api.config.rabbitmqconfig;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue customerZipCodeResponseQueue() {
        return new Queue("customerzipcode.response.queue");
    }

    @Bean
    public Queue productZipCodeResponseQueue() {
        return new Queue("productzipcode.response.queue");
    }

    @Bean
    public Queue zipCodeRequestQueue() {
        return new Queue("zipcode.request.queue");
    }

    @Bean
    public Queue freightCalculated() {
        return new Queue("distance.calculated.queue");
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("distance.exchange");
    }

}
