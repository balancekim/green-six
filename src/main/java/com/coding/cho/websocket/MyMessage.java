package com.coding.cho.websocket;

import lombok.Data;

@Data
public class MyMessage {
	private String key;
	private String name;
	private String content;
}
