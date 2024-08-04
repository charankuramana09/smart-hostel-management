package com.admin.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import com.admin.dto.UserDetailsResponseDto;
import com.admin.entity.UserDetails;
import com.admin.repository.AdminRepository;
import com.admin.service.AdminService;


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


	
	public Map<String, Object> validateMobileNumbers(List<Long> mobileNumbers) {
        // Fetch existing mobile numbers
        List<Long> existingMobileNumbers = adminRepository.findExistingMobileNumbers(mobileNumbers);

        // Determine non-existing mobile numbers
        List<Long> nonExistingMobileNumbers = mobileNumbers.stream()
            .filter(mobileNumber -> !existingMobileNumbers.contains(mobileNumber))
            .collect(Collectors.toList());

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("existingMobileNumbers", existingMobileNumbers);
        response.put("nonExistingMobileNumbers", nonExistingMobileNumbers);

        return response;
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
