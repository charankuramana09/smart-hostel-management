package com.admin.controller;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admin.dto.UserDetailsResponseDto;
import com.admin.entity.UserDetails;
import com.admin.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	 private AdminService adminService;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	public ResponseEntity<List<UserDetails>> findAllUserDetails(){
		
		return new ResponseEntity<List<UserDetails>>(adminService.findAllUsers(),HttpStatus.OK);
	}
	
	
	@GetMapping("/filter/hostelname")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	public ResponseEntity<List<Object[]>> getUserDetailsByHostelName(@RequestParam String hostelName) {
		List<Object[]> details = adminService.getUserDetailsByHostelName( hostelName);
		return ResponseEntity.ok(details);
	}
	
	   @GetMapping("/filter")
	   @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	    public ResponseEntity<List<Object[]>> getUserDetailsByFrequencyType(@RequestParam String frequencyType, @RequestParam String hostelName) {
	        List<Object[]> details = adminService.getUserDetailsByFrequencyType(frequencyType, hostelName);
	        return ResponseEntity.ok(details);
	    }
	   
	       
	    
	    @PatchMapping("/patch/{userId}")
	    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
	    public ResponseEntity<UserDetailsResponseDto> patchUserDetails(@PathVariable long userId, @RequestBody Map<String, Object> updates) {
	        try {
	            UserDetailsResponseDto updatedUser = adminService.patchUserDetails(userId, updates);
	            return ResponseEntity.ok(updatedUser);
	        }catch (Exception e) {
				System.out.println(e);
			}
			return null;
	    }

	   
}  
	
