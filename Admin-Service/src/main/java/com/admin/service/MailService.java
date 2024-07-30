package com.admin.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.admin.dto.MailRequestEmail;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;

	public void signup(MailRequestEmail req) {
		
		sendConfirmationEmail(req.getEmail(),req.getName());
	}
	

	
   public void sendConfirmationEmail(String email, String name) {
	   
	   SimpleMailMessage message = new SimpleMailMessage();
	   message.setTo(email);
	   message.setSubject(name);
	   message.setText("  Suceessfully   Registried... ");
	   mailSender.send(message);
   }
}