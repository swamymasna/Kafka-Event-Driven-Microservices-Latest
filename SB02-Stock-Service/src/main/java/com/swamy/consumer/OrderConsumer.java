package com.swamy.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.swamy.StockRepository;
import com.swamy.payload.OrderEvent;

@Component
public class OrderConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);
	
	@Autowired
	private StockRepository stockRepository;
	
	@KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topic.name}")
	public void consumeOrder(OrderEvent orderEvent) {

		LOGGER.info("ENTERED INTO consumeOrder() METHOD");
		
		LOGGER.info("Order Consumed in Stock Service : " + orderEvent);
		OrderEvent savedOrder = stockRepository.save(orderEvent);
		System.out.println("Order Saved into the Database : " + savedOrder);
		System.out.println("Order Consumed in Stock Service : " + orderEvent);
		
	}
}
