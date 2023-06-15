package com.microservices.orderservice.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.email.queue.name}")
    private String email_queue;

    @Value("${rabbitmq.stock.queue.name}")
    private String stock_queue;

    @Value("${rabbitmq.email.routing.key.name}")
    private String email_routing_key;

    @Value("${rabbitmq.stock.routing.key.name}")
    private String stock_routing_key;

    @Bean
    public Queue emailQueue()
    {
        return new Queue(email_queue);
    }

    @Bean
    public Queue stockQueue()
    {
        return new Queue(stock_queue);
    }

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding queueBinding()
    {
        return BindingBuilder.bind(emailQueue())
                .to(exchange())
                .with(email_routing_key);
    }

    @Bean
    public Binding stockBinding()
    {
        return BindingBuilder.bind(stockQueue())
                .to(exchange())
                .with(stock_routing_key);
    }

    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
