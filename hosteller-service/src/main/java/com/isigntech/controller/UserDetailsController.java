package com.isigntech.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isigntech.ResponseDto.UserDetailsResponseDto;
import com.isigntech.Service.UserDetailsService;

@RestController
@RequestMapping("/user")
public class UserDetailsController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<UserDetailsResponseDto> saveUser(@RequestParam("userDetails") String userDetailsResponseDto, @RequestParam("file") MultipartFile file) throws IOException{
		UserDetailsResponseDto value = new ObjectMapper().readValue(userDetailsResponseDto, UserDetailsResponseDto.class);
		value.setIdProof(file.getBytes());
		return new ResponseEntity<UserDetailsResponseDto>(userDetailsService.saveUserDetails(value),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getId/{userId}")
	@PreAuthorize("hasAnyRole('ROLE_USER')")
	public ResponseEntity<UserDetailsResponseDto> getById(@PathVariable long userId){
		
		return new ResponseEntity<UserDetailsResponseDto>(userDetailsService.getById(userId), HttpStatus.OK);
	}
	
	
	
	
	 @PutMapping("/update{userId}")
	 @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR','ROLE_USER')")
	    public ResponseEntity<UserDetailsResponseDto> updateUser(
	            @RequestParam("userDetails") String userDetailsResponseDto,
	            @RequestParam("file") MultipartFile file,
	            @RequestParam("userId") Long userId) throws IOException {

	        ObjectMapper objectMapper = new ObjectMapper();
	        UserDetailsResponseDto value = objectMapper.readValue(userDetailsResponseDto, UserDetailsResponseDto.class);
	        value.setIdProof(file.getBytes());

	        UserDetailsResponseDto updatedUserDetails = userDetailsService.updateUserDetails(value, userId);
	        return new ResponseEntity<>(updatedUserDetails, HttpStatus.OK);
	    }
	 @GetMapping("/updatePaymentStatus")
	 @PreAuthorize("hasAnyRole('ROLE_USER')")
	 public ResponseEntity<String> updatePaymentStatus(Long userId, String paymentStatus) {
		 return ResponseEntity.ok("updated");
	 }
		 
		 

}
