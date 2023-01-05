package com.swamy.payload;

import lombok.Data;

@Data
public class OrderEvent {

	private String message;
	private String status;
	private Order order;
}
