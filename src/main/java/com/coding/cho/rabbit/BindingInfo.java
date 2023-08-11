package com.coding.cho.rabbit;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BindingInfo {
	
	private String queueName;
    private String exchangeName;
    private String routingKey;

}
