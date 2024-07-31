package com.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.entity.UserDetails;
import com.admin.repository.AdminRepository;


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
