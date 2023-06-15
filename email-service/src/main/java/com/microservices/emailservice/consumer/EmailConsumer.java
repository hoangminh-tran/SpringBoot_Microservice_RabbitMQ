package com.microservices.emailservice.consumer;

import com.microservices.emailservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = "${rabbitmq.email.queue.name}")
    public void getMessage(OrderEvent orderEvent)
    {
        LOGGER.info("Get message " + orderEvent.toString() + "in Email Service");
    }
}
