package com.microservices.orderservice.controller;

import com.microservices.orderservice.dto.Order;
import com.microservices.orderservice.producer.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderProducer orderProducer;

    @PostMapping("/send")
    public ResponseEntity<Order> sendMessage(@RequestBody Order order)
    {
        orderProducer.sendMessage(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
