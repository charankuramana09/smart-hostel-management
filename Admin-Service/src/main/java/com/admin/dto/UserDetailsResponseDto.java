package com.admin.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsResponseDto {

	
	long userId;
	String firstName;
	String lastName;
	String gender;
	Date joiningDate;
	String purpose;
	String roomSharing;
	String frequency;
	String userType;
	long mobileNumber;
	long alternateMobileNumber;
	String email;
	byte[] idProof;
	boolean status;
	double paidAmount;
	double pendingAmount;
	double advancePayment;
	String hostelName;

	public UserDetailsResponseDto(String firstName, String lastName, String email ) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
	}

}
