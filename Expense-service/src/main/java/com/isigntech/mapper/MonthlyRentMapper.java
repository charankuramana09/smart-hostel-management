package com.isigntech.mapper;

import com.isigntech.dto.MonthlyRentDto;
import com.isigntech.entity.MonthlyRent;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class MonthlyRentMapper {

	 @Autowired
	    private ModelMapper modelMapper;

	    public MonthlyRentDto mapToMonthlyRentDto(MonthlyRent monthlyRent) {
	        MonthlyRentDto monthlyRentDto = modelMapper.map(monthlyRent, MonthlyRentDto.class);
	        monthlyRentDto.setReceiptFile(null); // Do not map the file path back to MultipartFile
	        return monthlyRentDto;
	    }

	    public MonthlyRent mapToMonthlyRent(MonthlyRentDto monthlyRentDto) {
	        MonthlyRent monthlyRent = modelMapper.map(monthlyRentDto, MonthlyRent.class);
	        if (monthlyRentDto.getReceiptFile() != null && !monthlyRentDto.getReceiptFile().isEmpty()) {
	            monthlyRent.setReceiptAttached(monthlyRentDto.getReceiptFile().getOriginalFilename());
	        }
	        return monthlyRent;
	    }
}
