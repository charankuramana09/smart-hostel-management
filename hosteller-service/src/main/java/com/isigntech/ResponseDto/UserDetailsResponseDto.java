package com.isigntech.ResponseDto;

import java.util.Date;

import lombok.Data;

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
	boolean status=true;
	double paidAmount;
	double pendingAmount;
	double advancePayment;
	String hostelName;
	Date paymentETA;
	private String paymentStatus;
	int roomNumber;
	String roomType;

}
