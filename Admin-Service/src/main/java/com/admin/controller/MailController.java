package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.MailRequestEmail;
import com.admin.service.MailService;


@RestController
public class MailController {
	
	@Autowired
	private MailService signupService;
	
	
	@PostMapping("/mail")
	public void signup(@RequestBody MailRequestEmail req ) {
		
		signupService.signup(req);
	}
	

}
