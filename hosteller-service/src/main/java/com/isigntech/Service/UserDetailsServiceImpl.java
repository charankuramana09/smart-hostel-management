package com.isigntech.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		 UserDetails userDetails = modelMapper.map( userDetailsResponseDto,UserDetails.class);
		 
		 userDetails = userDetailsRepository.save(userDetails);
		 
		 UserDetailsResponseDto detailsResponseDto = modelMapper.map(userDetails, UserDetailsResponseDto.class);
		
		return  detailsResponseDto;
	}



	@Override
	public UserDetailsResponseDto getById(long userId) {
		 UserDetails userDetails = userDetailsRepository.findById(userId).get();
		 UserDetailsResponseDto userDetailsResponseDto = modelMapper.map(userDetails, UserDetailsResponseDto.class);
		return userDetailsResponseDto;
	}



	@Override
	public List<UserDetailsResponseDto> findAllUsers(UserDetailsResponseDto userDetailsResponseDto) {
		
		List<UserDetails> userDetails = userDetailsRepository.findAll();
		List<UserDetailsResponseDto> detailsResponseDtos = new ArrayList<>();
		
		for (UserDetails student : userDetails) {
			UserDetailsResponseDto userDto = modelMapper.map(student, UserDetailsResponseDto.class);
			detailsResponseDtos.add(userDto);
		}
		
		return detailsResponseDtos;
	}
	
	

	
	

	

	

}
