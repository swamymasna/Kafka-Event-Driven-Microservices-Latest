package com.swamy.payload;

import lombok.Data;

@Data
public class Order {

	private String orderId;
	private String name;
	private Double qty;
	private Double price;
	
}
