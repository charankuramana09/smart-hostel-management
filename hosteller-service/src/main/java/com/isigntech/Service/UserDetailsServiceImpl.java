package com.isigntech.Service;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriUtils;

import com.isigntech.Model.UserDetails;
import com.isigntech.Repository.UserDetailsRepository;
import com.isigntech.ResponseDto.UserDetailsResponseDto;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDetailsResponseDto saveUserDetails(UserDetailsResponseDto userDetailsResponseDto) {

		UserDetails userDetails = modelMapper.map(userDetailsResponseDto, UserDetails.class);

		userDetails = userDetailsRepository.save(userDetails);

		UserDetailsResponseDto detailsResponseDto = modelMapper.map(userDetails, UserDetailsResponseDto.class);

		return detailsResponseDto;
	}

	@Override
	public UserDetailsResponseDto getById(long userId) {
		UserDetails userDetails = userDetailsRepository.findById(userId).get();
		UserDetailsResponseDto userDetailsResponseDto = modelMapper.map(userDetails, UserDetailsResponseDto.class);
		return userDetailsResponseDto;
	}


	public UserDetailsResponseDto updateUserDetails(UserDetailsResponseDto userDetailsResponseDto, long userId) {
        // Fetch existing user details
        UserDetails existingUserDetails = userDetailsRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        
        existingUserDetails.setFirstName(userDetailsResponseDto.getFirstName());
        existingUserDetails.setLastName(userDetailsResponseDto.getLastName());
        existingUserDetails.setAdvancePayment(userDetailsResponseDto.getAdvancePayment());
        existingUserDetails.setAlternateMobileNumber(userDetailsResponseDto.getAlternateMobileNumber());
        existingUserDetails.setEmail(userDetailsResponseDto.getEmail());
        existingUserDetails.setGender(userDetailsResponseDto.getGender());
        existingUserDetails.setFrequency(userDetailsResponseDto.getFrequency());
        existingUserDetails.setHostelName(userDetailsResponseDto.getHostelName());
        existingUserDetails.setIdProof(userDetailsResponseDto.getIdProof());
        existingUserDetails.setJoiningDate(userDetailsResponseDto.getJoiningDate());
        existingUserDetails.setMobileNumber(userDetailsResponseDto.getMobileNumber());
        existingUserDetails.setPaidAmount(userDetailsResponseDto.getPaidAmount());
        existingUserDetails.setPaymentETA(userDetailsResponseDto.getPaymentETA());
        existingUserDetails.setIdProof(userDetailsResponseDto.getIdProof());
        existingUserDetails.setPurpose(userDetailsResponseDto.getPurpose());
        existingUserDetails.setStatus(userDetailsResponseDto.isStatus());
        existingUserDetails.setRoomNumber(userDetailsResponseDto.getRoomNumber());
        existingUserDetails.setUserType(userDetailsResponseDto.getUserType());
       
        

        // Save updated user details
        UserDetails updatedUserDetails = userDetailsRepository.save(existingUserDetails);

        // Convert entity to response DTO
        UserDetailsResponseDto detailsResponseDto = modelMapper.map(updatedUserDetails, UserDetailsResponseDto.class);
        return detailsResponseDto;
    }
	@Override
	public String updatePaymentStatus(Long userId, String paymentInfo) {
	    // Decode the query parameters into a map
	    Map<String, String> paymentStatusMap = decodeQueryParamToMap(paymentInfo);

	    // Log the map to verify its content
	    System.out.println("Payment Status Map: " + paymentStatusMap);

	    // Get the status from the map
	    String status = paymentStatusMap.get("status");
	    System.out.println("Status: " + status); // Log status value

	    if (status != null) {
	        // Get the payment amount from the map
	        String paymentAmount = paymentStatusMap.get("paymentAmount");
	        System.out.println("Payment Amount: " + paymentAmount); // Log payment amount

	        double paidAmount;
	        try {
	            paidAmount = Double.parseDouble(paymentAmount);
	        } catch (NumberFormatException e) {
	            paidAmount = 0.0;  
	            System.err.println("Invalid payment amount format: " + e.getMessage());
	        }
	        
	         UserDetails userDetails = userDetailsRepository.findById(userId).get();
	         if(userDetails==null) {
	        	 throw new NoSuchElementException("No user id find");
	         }
	         this.setSharingPrice(userDetails, status, paidAmount);
	        userDetailsRepository.updateByUserId(userId, status, paidAmount);
	    } else {
	        
	        userDetailsRepository.updateByUserId(userId, "UNKNOWN", 0);
	    }

	    return "updated";
	}

	private Map<String, String> decodeQueryParamToMap(String paymentInfo) {
	    Map<String, String> map = new HashMap<>();
	    try {
	        // URL decode the input string
	        String decodedString = URLDecoder.decode(paymentInfo, StandardCharsets.UTF_8.name());
	        System.out.println("Decoded Payment Info: " + decodedString); // Log for debugging
	        
	        String[] params = decodedString.split("&");
	        for (String param : params) {
	            String[] keyValue = param.split("=");
	            if (keyValue.length == 2) {
	                String key = keyValue[0];
	                String value = keyValue[1];
	                map.put(key, value);
	                System.out.println("Key: " + key + ", Value: " + value); // Log each key-value pair
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Error decoding query parameters: " + e.getMessage());
	    }
	    return map;
	}
	
	public UserDetails setSharingPrice(UserDetails userDetails,String status,Double paidAmount) {
		String roomSharing = userDetails.getRoomSharing();
		if(roomSharing.equalsIgnoreCase("1 Sharing")){
			if(paidAmount==7000) {
				userDetails.setPaidAmount(paidAmount);
			}else {
				Double pendingAmount=7000-paidAmount;
				userDetails.setPaidAmount(paidAmount);
				userDetails.setPendingAmount(pendingAmount);
			}
		}else if(roomSharing.equalsIgnoreCase("2 Sharing")){
			if(paidAmount==6000) {
				userDetails.setPaidAmount(paidAmount);
			}else {
				Double pendingAmount=6000-paidAmount;
				userDetails.setPaidAmount(paidAmount);
				userDetails.setPendingAmount(pendingAmount);
			}
		}else if(roomSharing.equalsIgnoreCase("3 Sharing")){
			if(paidAmount==5000) {
				userDetails.setPaidAmount(paidAmount);
			}else {
				Double pendingAmount=5000-paidAmount;
				userDetails.setPaidAmount(paidAmount);
				userDetails.setPendingAmount(pendingAmount);
			}
		}else if(roomSharing.equalsIgnoreCase("4 Sharing")){
			if(paidAmount==4000) {
				userDetails.setPaidAmount(paidAmount);
			}else {
				Double pendingAmount=4000-paidAmount;
				userDetails.setPaidAmount(paidAmount);
				userDetails.setPendingAmount(pendingAmount);
			}
		}
		return userDetails;
		
	}
	
	@Override
    public UserDetailsResponseDto patchUserDetails(long userId, Map<String, Object> updates) {
        UserDetails user = userDetailsRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        updates.forEach((key, value) -> {
            try {
                Field field = UserDetails.class.getDeclaredField(key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, user, value);
            } catch (NoSuchFieldException e) {
                // Log the exception
                System.err.println("Failed to update field: " + key + " with value: " + value);
            }
        });

        UserDetails savedUser = userDetailsRepository.save(user);
        return mapToDto(savedUser);
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
	
	 public UserDetailsResponseDto getUserByEmail(String email) {
	        UserDetails user = userDetailsRepository.findByEmail(email);
	        UserDetailsResponseDto userDetailsResponseDto = modelMapper.map(user, UserDetailsResponseDto.class);
			return userDetailsResponseDto;
	    }

	@Override
	public Boolean getUserDataBoolean(String email) {
		UserDetails userByEmail = userDetailsRepository.findByEmail(email);
		if(userByEmail!=null) {
			return true;
		}
		return false;
	}

}
