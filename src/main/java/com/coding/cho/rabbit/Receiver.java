package com.coding.cho.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.coding.cho.websocket.MyMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Receiver {
	
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	@RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
	public void receiveMessage(MyMessage message) {
		System.out.println(">>>>수신된 메세지:"+ message);
		simpMessagingTemplate.convertAndSend("/topic/order/"+message.getKey(), message);
	}

}
