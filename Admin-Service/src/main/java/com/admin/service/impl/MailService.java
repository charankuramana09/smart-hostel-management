package com.admin.service;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.admin.dto.MailRequestEmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;

	// It is working local-file and object type..
//	public void signup(MailRequestEmail req , File attachment ) throws MessagingException {
//		
//		sendConfirmationEmail(req.getEmail(),req.getName(), attachment);
//	}
	
	public void signup( String email, String name, File attachment ) throws MessagingException {
		
		sendConfirmationEmail(email,name, attachment);
	}
	

	
   public void sendConfirmationEmail(String email, String name, File attachment) throws MessagingException {
	   
	   
	   // It will be working only Text..
//	   SimpleMailMessage message = new SimpleMailMessage();
//	   message.setTo(email);
//	   message.setSubject(name);
//	   message.setText("  Suceessfully   Registried... ");
//	   mailSender.send(message);
	   
	   // It will be working as Text and File. 
	   MimeMessage message = mailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(message, true);

       helper.setTo(email);
       helper.setSubject(name);
       helper.setText(" Suceessfully   Registried... ");

       FileSystemResource file = new FileSystemResource(attachment);
       helper.addAttachment(file.getFilename(), file);

       mailSender.send(message);
	   
   }
}