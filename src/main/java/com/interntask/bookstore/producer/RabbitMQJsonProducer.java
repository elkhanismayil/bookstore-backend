package com.interntask.bookstore.producer;

import com.interntask.bookstore.model.response.BookResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQJsonProducer {
    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing.json.key}")
    private String routingJsonKey;


    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(BookResponse bookResponse) {
        rabbitTemplate.convertAndSend(exchangeName, routingJsonKey, bookResponse);
        log.info(String.format("Json message sent -----> %s", bookResponse.getBookName()));
    }
}
