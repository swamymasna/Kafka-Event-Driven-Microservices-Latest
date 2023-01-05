package com.swamy.service.impl;

import java.io.File;
import java.io.FileInputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.swamy.payload.OrderEvent;
import com.swamy.service.IEmailService;
import com.swamy.utils.AppConstants;

@Service
public class EmailSenderService implements IEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;
	
	@Value("${spring.mail.username}")
	private String receiver;
	
	@Override
	public String sendEmail(OrderEvent orderEvent) throws Exception {

		// Creating a mime message
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setFrom(AppConstants.SENDER);
		mimeMessageHelper.setTo(AppConstants.RECEIVER);
		
		String body = readMailBody("ORDER-EMAIL-BODY-TEMPLATE.txt", orderEvent);
		
		mimeMessageHelper.setSubject(AppConstants.ORDER_DETAILS);
//		mimeMessageHelper.setText(orderEvent.toString());
		mimeMessageHelper.setText(body);

		// Adding the attachment
		FileSystemResource file = new FileSystemResource(new File(AppConstants.PATH));

		mimeMessageHelper.addAttachment(file.getFilename(), file);

		// Sending the mail
		javaMailSender.send(mimeMessage);
		return AppConstants.SUCCESS;

	}
	
	public String readMailBody(String fileName , OrderEvent orderEvent) {
		String mailBody = null;
		int count =0;

		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			byte[] bytesArray = new byte[(int)file.length()];
			fis.read(bytesArray);
			mailBody = new String(bytesArray);

			mailBody = mailBody.replace(AppConstants.MAIL_SENDER, AppConstants.SENDER); 
			mailBody = mailBody.replace(AppConstants.MESSAGE, orderEvent.getMessage()); 
			mailBody = mailBody.replace(AppConstants.STATUS, orderEvent.getStatus());	
			mailBody = mailBody.replace(AppConstants.ORDER_ID, orderEvent.getOrder().getOrderId());	
			mailBody = mailBody.replace(AppConstants.ORDER_NAME, orderEvent.getOrder().getName());	
			mailBody = mailBody.replace(AppConstants.QTY, orderEvent.getOrder().getQty().toString());	
			mailBody = mailBody.replace(AppConstants.PRICE, orderEvent.getOrder().getPrice().toString());	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return mailBody;
	}

}