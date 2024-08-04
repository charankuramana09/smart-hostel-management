package com.isigntech.Service;

import java.util.List;
import java.util.Map;

import com.isigntech.Model.UserDetails;
import com.isigntech.ResponseDto.UserDetailsResponseDto;

public interface UserDetailsService {

	public UserDetailsResponseDto saveUserDetails(UserDetailsResponseDto userDetailsResponseDto);

	public UserDetailsResponseDto getById(long userId);

	public UserDetailsResponseDto updateUserDetails(UserDetailsResponseDto userDetailsResponseDto, long userId);

	public String updatePaymentStatus(Long userId, String paymentStatus);

	UserDetailsResponseDto patchUserDetails(long userId, Map<String, Object> updates);
	
	public UserDetailsResponseDto getUserByEmail(String email);
	public Boolean getUserDataBoolean(String email);
	

}
