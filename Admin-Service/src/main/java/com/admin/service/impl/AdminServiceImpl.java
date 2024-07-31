package com.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.UserDetails;
import com.admin.repository.AdminRepository;
import com.admin.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService {
	
    @Autowired
	private AdminRepository  adminRepository;
    


	@Override
	public List<UserDetails> findAllUsers() {
		
		List<UserDetails> userDetails = adminRepository.findAll();
	
		return userDetails;
	}


	
	
	  public List<Object[]> getUserDetailsByFrequencyType(String frequencyType, String hostelName) {
	        return adminRepository.findByFrequencyType(frequencyType, hostelName);
	    }
	


	

	
	    
}
