package com.microservices.stockservice.consumer;

import com.microservices.stockservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(StockConsumer.class);

    @RabbitListener(queues = "${rabbitmq.stock.queue.name}")
    public void getMessage(OrderEvent orderEvent)
    {
        LOGGER.info("Get message " + orderEvent.toString() + "in Email Service");
    }
}
