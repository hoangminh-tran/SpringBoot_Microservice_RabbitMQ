package com.microservices.orderservice.producer;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.microservices.orderservice.dto.Order;
import com.microservices.orderservice.dto.OrderEvent;
import com.microservices.orderservice.dto.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.email.routing.key.name}")
    private String email_routing_key;

    @Value("${rabbitmq.stock.routing.key.name}")
    private String stock_routing_key;

    private final RabbitTemplate rabbitTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    public void sendMessage(Order order)
    {
        order.setId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent(OrderStatus.PENDING, "This message " + order.toString() + " is in Pending state", order);
        LOGGER.info("Send message " +  orderEvent);
        rabbitTemplate.convertAndSend(exchange, email_routing_key, orderEvent);
        rabbitTemplate.convertAndSend(exchange, stock_routing_key, orderEvent);
    }
}
