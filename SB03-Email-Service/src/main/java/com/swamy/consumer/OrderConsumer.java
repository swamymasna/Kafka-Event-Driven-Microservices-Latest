package com.swamy.consumer;

import java.io.File;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.swamy.payload.OrderEvent;
import com.swamy.service.impl.EmailSenderService;
import com.swamy.utils.AppConstants;

@Component
public class OrderConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

	@Autowired
	private EmailSenderService emailSenderService;

	@KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = "${kafka.topic.name}")
	public void consumeOrder(OrderEvent orderEvent) throws Exception {

		LOGGER.info("ENTERED INTO consumeOrder() METHOD");

		LOGGER.info("Order Received in Email Service : " + orderEvent);

		emailSenderService.sendEmail(orderEvent);
		LOGGER.info("Email Sent Successfully");
		System.out.println("Email Sent Successfully");

		System.out.println("Order Received in Email Service : " + orderEvent);

	}
	
	
}
