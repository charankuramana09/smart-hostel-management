package com.admin.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.admin.dto.UserDetailsResponseDto;
import com.admin.entity.UserDetails;

import jakarta.mail.Multipart;


public interface AdminService {

	//all data 
	 public List<UserDetails> findAllUsers();
	 
	 public List<UserDetails> findByHostelName( String hostelName);

	 //filtering frequencyType like monthly,daily..
	 public List<Object[]> getUserDetailsByFrequencyType(String frequencyType, String hostelName);

	 
	 //update 
	  public UserDetailsResponseDto patchUserDetails(long userId, Map<String, Object> updates);

}
