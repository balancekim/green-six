package com.coding.cho.websocket;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class WebSocketController {
	
	
	private final RabbitTemplate rabbitTemplate;
	
	//@Value("${spring.rabbitmq.template.exchange}")
	private String exchange="test.queue.exchange";
	
	
	@MessageMapping("/order") // /green/order 
	//@SendTo("/topic/msg")//js : 구독하는 하는 클라이언트에 이벤트가 발생
	public void sendMsg(MyMessage message) {
		rabbitTemplate.convertAndSend(exchange, "green."+message.getName(), message);
		
	}
	
	@MessageMapping("/hello") // /green/hello
	//@SendTo("/topic/msg")
	public void helloMsg(MyMessage message) {
		//#에 들어가는 정보
		rabbitTemplate.convertAndSend(exchange, "green."+message.getName(), message);
	}

}
