package com.isigntech.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public String updatePaymentStatus(Long userId, String paymentStatus) {
		userDetailsRepository.updateByUserId(userId, paymentStatus);
		return "updated";
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

}
