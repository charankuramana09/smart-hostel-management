package com.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.dto.UserDetailsDTO;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	  @GetMapping("/s")
	    public void receiveHostlerFromHostlerService() {
	    
		  UserDetailsDTO us = new UserDetailsDTO();
		  
		  System.out.println(" DTO : "+ us.getFullName());
		  
		  System.out.println(" Email " + us.getEmail() );
	    }
	  

}
