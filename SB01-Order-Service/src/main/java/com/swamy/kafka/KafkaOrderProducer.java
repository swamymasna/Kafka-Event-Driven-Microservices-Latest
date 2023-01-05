package com.swamy.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.swamy.payload.OrderEvent;

@Service
public class KafkaOrderProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOrderProducer.class);

	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;

	@Value("${kafka.topic.name}")
	private String topic;

	public void placeOrder(OrderEvent orderEvent) {

		LOGGER.info("Entered into placeOrder() method");

		kafkaTemplate.send(topic, orderEvent);

		LOGGER.info("Placed Order : " + orderEvent);
	}

}
