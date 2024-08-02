package com.isigntech.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRentDto {

	
	private Long id;
    private String hostelName;
    private String ownerName;
    private Date paidDate;
    private Double paidAmount;
    private MultipartFile receiptFile;  
    private Double totalAmount;
}
