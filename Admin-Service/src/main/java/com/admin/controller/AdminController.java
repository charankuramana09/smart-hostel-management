package com.admin.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.entity.UserDetails;
import com.admin.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	 private AdminService adminService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDetails>> findAllUserDetails(){
		
		return new ResponseEntity<List<UserDetails>>(adminService.findAllUsers(),HttpStatus.OK);
	}
	

	
	
	   @GetMapping("/filter")
	    public ResponseEntity<List<Object[]>> getUserDetailsByFrequencyType(@RequestParam String frequencyType, @RequestParam String hostelName) {
	        List<Object[]> details = adminService.getUserDetailsByFrequencyType(frequencyType, hostelName);
	        return ResponseEntity.ok(details);
	    }
	
	
}
