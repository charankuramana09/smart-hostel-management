package com.admin.service.impl;


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
	
	public void sendInvioceMail(String email, String name, File attachment) throws MessagingException {
		   MimeMessage message = mailSender.createMimeMessage();
	       MimeMessageHelper helper = new MimeMessageHelper(message, true);

	       helper.setTo(email);
	       helper.setSubject(name);
	       helper.setText("Invoice details... ");

	       FileSystemResource file = new FileSystemResource(attachment);
	       helper.addAttachment(file.getFilename(), file);

	       mailSender.send(message);
	   }


   
   public void sendSuccessEmail(String email, String name) throws MessagingException {
	 
	   MimeMessage message = mailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       helper.setTo(email);
       helper.setSubject("Your registration is SuccessFull");
       helper.setText("Hii "+name+" \n your registration has been completed Suceessfully... ");
       mailSender.send(message);
	   
   }
}