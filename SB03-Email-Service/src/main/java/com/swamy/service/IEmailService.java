package com.swamy.service;

import com.swamy.payload.OrderEvent;

public interface IEmailService {

	public String sendEmail(OrderEvent orderEvent) throws Exception;
}

