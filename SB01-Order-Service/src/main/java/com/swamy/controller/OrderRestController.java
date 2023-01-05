package com.swamy.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.kafka.KafkaOrderProducer;
import com.swamy.payload.Order;
import com.swamy.payload.OrderEvent;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

	@Autowired
	private KafkaOrderProducer orderProducer;

	@PostMapping
	public String sendMessage(@RequestBody Order order) {

		order.setOrderId(UUID.randomUUID().toString());

		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("Order Status is in Pending State..!");
		orderEvent.setOrder(order);

		orderProducer.placeOrder(orderEvent);

		return "Order Placed Successfully";
	}
}
