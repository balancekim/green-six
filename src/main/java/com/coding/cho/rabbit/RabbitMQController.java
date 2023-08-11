package com.coding.cho.rabbit;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RabbitMQController {
	
    private final RabbitMQService rabbitMQService;
    
    

    /**
     * 메시지 큐 생성 엔드포인트
     *
     * @param queueName 생성할 메시지 큐의 이름
     * @return 생성 결과 메시지
     */
    @PostMapping("/rabbit/queues/{queueName}")
    public String createQueue(@PathVariable String queueName) {
        rabbitMQService.createQueue(queueName);
        return "Queue " + queueName + " created successfully!";
    }

    /**
     * 익스체인지 생성 엔드포인트
     *
     * @param exchangeName 생성할 익스체인지의 이름
     * @return 생성 결과 메시지
     */
    @PostMapping("/rabbit/exchanges/{exchangeName}")
    public String createExchange(@PathVariable String exchangeName) {
        rabbitMQService.createExchange(exchangeName);
        return "Exchange " + exchangeName + " created successfully!";
    }

    /**
     * 큐와 익스체인지 바인딩 엔드포인트
     *
     * @param bindingInfo 바인딩에 필요한 정보를 담은 DTO 객체
     * @return 바인딩 결과 메시지
     */
    @PostMapping("/rabbit/bindings")
    public String bindQueueToExchange(@RequestBody BindingInfo bindingInfo) {
        rabbitMQService.bindQueueToExchange(bindingInfo.getQueueName(), bindingInfo.getExchangeName(), bindingInfo.getRoutingKey());
        return "Queue " + bindingInfo.getQueueName() + " bound to Exchange " + bindingInfo.getExchangeName() + " with routing key " + bindingInfo.getRoutingKey();
    }

    /**
     * 메시지 발행 엔드포인트
     *
     * @param messageInfo 발행에 필요한 정보를 담은 DTO 객체
     * @return 발행 결과 메시지
     */
    @PostMapping("/rabbit/messages")
    public String sendMessage(@RequestBody MessageInfo messageInfo) {
        rabbitMQService.sendMessage(messageInfo.getExchangeName(), messageInfo.getRoutingKey(), messageInfo.getMessage());
        return "Message sent successfully!";
    }

    /**
     * 메시지 수신 엔드포인트
     *
     * @param queueName 수신할 메시지 큐의 이름
     * @return 수신한 메시지
     */
    @GetMapping("/rabbit/messages/{queueName}")
    public String receiveMessage(@PathVariable String queueName) {
        Object message = rabbitMQService.receiveMessage(queueName);
        return "Received message: " + message;
    }

}
