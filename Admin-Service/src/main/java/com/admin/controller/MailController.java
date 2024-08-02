package com.admin.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admin.dto.MailRequestEmail;
import com.admin.service.impl.MailService;

import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/email")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	//local file it will take
//	ClassLoader classLoader = getClass().getClassLoader();
//	File file = new File(classLoader.getResource("testfile.pdf").getFile());

		
	@PostMapping("/sendInvoice")
	public String mail(@RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException  {
		
		try {
			// Convert MultipartFile to File
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            
			mailService.mail(email ,name, convFile);
			return "Email sent successfully";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Failed to send email";
		}
	}
	
	@PostMapping("/signupSuccessEmail")
	public String mail(@RequestParam("email") String email,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
		
		try {
			mailService.sendSuccessEmail(email ,lastName+" "+firstName);
			return "Email sent successfully";
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Failed to send email";
		}
	}
	

}
