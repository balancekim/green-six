package com.coding.cho.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RabbitMQService {
	
	
	private final AmqpAdmin amqpAdmin;
	private final RabbitTemplate rabbitTemplate;
	
	// 큐 생성
    public void createQueue(String queueName) {
        Queue queue = new Queue(queueName);
        amqpAdmin.declareQueue(queue);
    }

    // 익스체인지 생성
    public void createExchange(String exchangeName) {
        //Exchange exchange = new DirectExchange(exchangeName);
        Exchange exchange = new TopicExchange(exchangeName);
        
        amqpAdmin.declareExchange(exchange);
    }

    // 큐와 익스체인지를 라우팅 키로 바인딩
    public void bindQueueToExchange(String queueName, String exchangeName, String routingKey) {
        Binding binding = BindingBuilder
                .bind(new Queue(queueName))
                .to(new TopicExchange(exchangeName))
                .with(routingKey);
        amqpAdmin.declareBinding(binding);
    }

    // 메시지 발행
    public void sendMessage(String exchangeName, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    // 메시지 수신
    public Object receiveMessage(String queueName) {
        return rabbitTemplate.receiveAndConvert(queueName);
    }

}
