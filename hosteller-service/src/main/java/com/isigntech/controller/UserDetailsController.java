package com.isigntech.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isigntech.ResponseDto.UserDetailsResponseDto;
import com.isigntech.Service.UserDetailsService;

@RestController
public class UserDetailsController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@PostMapping("/save")
	public ResponseEntity<UserDetailsResponseDto> saveUser(@RequestParam("userDetails") String userDetailsResponseDto, @RequestParam("file") MultipartFile file) throws IOException{
		UserDetailsResponseDto value = new ObjectMapper().readValue(userDetailsResponseDto, UserDetailsResponseDto.class);
		value.setIdProof(file.getBytes());
		return new ResponseEntity<UserDetailsResponseDto>(userDetailsService.saveUserDetails(value),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getId/{userId}")
	public ResponseEntity<UserDetailsResponseDto> getById(@PathVariable long userId){
		
		return new ResponseEntity<UserDetailsResponseDto>(userDetailsService.getById(userId), HttpStatus.OK);
	}
	
	@GetMapping("/findAllUsers")
	public ResponseEntity<List<UserDetailsResponseDto>> findAllUserDetails(UserDetailsResponseDto userDetailsResponseDto){
		
		return new ResponseEntity<List<UserDetailsResponseDto>>(userDetailsService.findAllUsers(userDetailsResponseDto),HttpStatus.OK);
	}
	
	


}
