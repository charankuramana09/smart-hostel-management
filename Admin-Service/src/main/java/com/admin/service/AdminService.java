package com.admin.service;

import java.util.List;

import com.admin.entity.UserDetails;


public interface AdminService {

	 public List<UserDetails> findAllUsers();
	 

	 //filtering frequencyType like monthly,daily..
	 public List<Object[]> getUserDetailsByFrequencyType(String frequencyType, String hostelName);  
}
