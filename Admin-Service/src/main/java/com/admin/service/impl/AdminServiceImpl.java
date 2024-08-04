package com.admin.service.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils.FieldFilter;

import com.admin.dto.UserDetailsResponseDto;
import com.admin.entity.UserDetails;
import com.admin.repository.AdminRepository;
import com.admin.service.AdminService;

import jakarta.transaction.Transactional;


@Service
public class AdminServiceImpl implements AdminService {
	
    @Autowired
	private AdminRepository  adminRepository;
    
    
    @Autowired
    private ModelMapper modelMapper;
    


	@Override
	public List<UserDetails> findAllUsers() {
		
		List<UserDetails> userDetails = adminRepository.findAll();
	
		return userDetails;
	}


	
	
	
	@Override
	 public List<UserDetails> findByHostelNameAndFrequency( String hostelName, String frequency){
		return adminRepository.findByHostelName( hostelName);
	}
	
	  public List<Object[]> getUserDetailsByFrequencyType(String frequencyType, String hostelName) {
	        return adminRepository.findByFrequencyType(frequencyType, hostelName);
	    }
	

	    
	  @Override
	  public UserDetailsResponseDto patchUserDetails(long userId, Map<String, Object> updates) {
	    UserDetails user = adminRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	    updates.forEach((key, value) -> {
	        try {
	          Field field = user.getClass().getDeclaredField(key); // Use getDeclaredField for custom class
	          field.setAccessible(true);
	          ReflectionUtils.setField(field, user, value);
	        } catch (NoSuchFieldException e) {
	        } 
	      });
	    return mapToDto(adminRepository.save(user));
	    
	}
	    
	    private UserDetailsResponseDto mapToDto(UserDetails user) {
	    	UserDetailsResponseDto dto = new UserDetailsResponseDto();
	    	dto.setUserId(user.getUserId());
	    	dto.setFirstName(user.getFirstName());
	    	dto.setLastName(user.getLastName());
	    	dto.setGender(user.getGender());
	    	dto.setJoiningDate(user.getJoiningDate());
	    	dto.setPurpose(user.getPurpose());
	    	dto.setRoomSharing(user.getRoomSharing());
	    	dto.setFrequency(user.getFrequency());
	    	dto.setUserType(user.getUserType());
	    	dto.setMobileNumber(user.getMobileNumber());
	    	dto.setAlternateMobileNumber(user.getAlternateMobileNumber());
	    	dto.setEmail(user.getEmail());
	    	dto.setIdProof(user.getIdProof());
	    	dto.setStatus(user.isStatus());
	    	dto.setPaidAmount(user.getPaidAmount());
	    	dto.setPendingAmount(user.getPendingAmount());
	    	dto.setAdvancePayment(user.getAdvancePayment());
	    	dto.setHostelName(user.getHostelName());
	    	dto.setPaymentETA(user.getPaymentETA());
	    	dto.setRoomNumber(user.getRoomNumber());
	    	dto.setRoomType(user.getRoomType());
	    	return dto;
	    }






	    
}
