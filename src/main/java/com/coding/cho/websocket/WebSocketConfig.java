package com.coding.cho.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//참고
//https://docs.spring.io/spring-framework/docs/5.3.29/reference/html/web.html#websocket-fallback

@Configuration
@EnableWebSocketMessageBroker//메세지 브로커가 지원하는 WebSocket메세지 처리를 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	//메모리 기반 메세지 브로커가
	//접두사가 붙은 목적지에서 클라이언트에게 인사말 메세지를 다시 전달 할 수 있도록 호출하는것
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
	//서버->클라이언트로 보내는 메세지 //구독 및 브로드캐스팅에 내장된 메시지 브로커를 사용하고 대상 헤더가 /topic `or `/queue브로커로 시작하는 메시지를 라우팅합니다.
    config.enableSimpleBroker("/topic");
    
    //클라이언트(웹) -> 서버에 메세지를 전송시 //대상 헤더가 로 시작하는 STOMP 메시지는 클래스 의 메서드 /green로 라우팅됩니다
    config.setApplicationDestinationPrefixes("/greensix");//ws://localhost:8080/green/uri --->
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
	//js: var socket=new SockJS("/green-six");//
    registry.addEndpoint("/green-six").withSockJS();//
    //WebSocket(또는 SockJS) 클라이언트가 WebSocket 핸드셰이크를 위해 연결해야 하는 끝점의 HTTP URL입니다.    
  }

}