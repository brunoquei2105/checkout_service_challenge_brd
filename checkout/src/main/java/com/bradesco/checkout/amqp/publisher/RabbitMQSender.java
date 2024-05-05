package com.bradesco.checkout.amqp.publisher;

import com.bradesco.checkout.model.Payment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.amqp.core.Queue;

@Service
@Slf4j
@AllArgsConstructor
public class RabbitMQSender {

    private final AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.queue}")
    private final Queue queue;

    public void publish (Payment payment){
        rabbitTemplate.convertAndSend(queue.getName(), payment);
        log.info("Sending Message to the Queue: "+ payment.toString());
    }
}