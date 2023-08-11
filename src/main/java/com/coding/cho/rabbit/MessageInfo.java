package com.coding.cho.rabbit;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageInfo {
	
	private String exchangeName;
    private String routingKey;
    private String message;

}
