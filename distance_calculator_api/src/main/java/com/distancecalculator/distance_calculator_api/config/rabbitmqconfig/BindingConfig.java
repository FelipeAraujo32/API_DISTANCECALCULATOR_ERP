package com.distancecalculator.distance_calculator_api.config.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingConfig {

    @Bean
    public Binding customerCepRequestBinding(Queue customerCepRequestQueue, TopicExchange freightExchange) {
        return BindingBuilder.bind(customerCepRequestQueue).to(freightExchange).with("customer.cep.request");
    }

    @Bean
    public Binding customerCepResponseBinding(Queue customerCepResponseQueue, TopicExchange freightExchange) {
        return BindingBuilder.bind(customerCepResponseQueue).to(freightExchange).with("customer.cep.response");
    }

    @Bean
    public Binding productCepRequestBinding(Queue productCepRequestQueue, TopicExchange freightExchange) {
        return BindingBuilder.bind(productCepRequestQueue).to(freightExchange).with("product.cep.request");
    }

    @Bean
    public Binding productCepResponseBinding(Queue productCepResponseQueue, TopicExchange freightExchange) {
        return BindingBuilder.bind(productCepResponseQueue).to(freightExchange).with("product.cep.response");
    }

    @Bean
    public Binding freightCalculatedBinding(Queue freightCalculated, TopicExchange freightExchange) {
        return BindingBuilder.bind(freightCalculated).to(freightExchange).with("freight.calculate.event");
    }
}