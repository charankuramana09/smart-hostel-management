package com.isigntech.Service;

import java.util.List;

import com.isigntech.ResponseDto.UserDetailsResponseDto;

public interface UserDetailsService {

	public UserDetailsResponseDto saveUserDetails(UserDetailsResponseDto userDetailsResponseDto);

	public UserDetailsResponseDto getById(long userId);

	public UserDetailsResponseDto updateUserDetails(UserDetailsResponseDto userDetailsResponseDto, long userId);

	public String updatePaymentStatus(Long userId, String paymentStatus);

}
