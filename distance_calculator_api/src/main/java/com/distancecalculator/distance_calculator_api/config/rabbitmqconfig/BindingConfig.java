package com.distancecalculator.distance_calculator_api.config.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BindingConfig {

    @Bean
    public Binding zipCodeRequestBinding(@Qualifier("zipCodeRequestQueue") Queue zipCodeRequestBinding, TopicExchange distanceExchange) {
        return BindingBuilder.bind(zipCodeRequestBinding).to(distanceExchange).with("zipcode.request");
    }

    @Bean
    public Binding customerZipCodeResponseBinding(@Qualifier("customerZipCodeResponseQueue") Queue customerZipCodeResponseBinding, TopicExchange distanceExchange) {
        return BindingBuilder.bind(customerZipCodeResponseBinding).to(distanceExchange).with("customerzipcode.response");
    }

    @Bean
    public Binding productZipCodeResponseBinding(@Qualifier("productZipCodeResponseQueue") Queue productZipCodeResponseBinding, TopicExchange distanceExchange) {
        return BindingBuilder.bind(productZipCodeResponseBinding).to(distanceExchange).with("productzipcode.response");
    }

    @Bean
    public Binding freightCalculatedBinding(@Qualifier("freightCalculated")Queue distanceCalculated, TopicExchange distanceExchange) {
        return BindingBuilder.bind(distanceCalculated).to(distanceExchange).with("distance.calculate.event");
    }
}