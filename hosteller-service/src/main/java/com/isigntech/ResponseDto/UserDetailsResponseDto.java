package com.isigntech.ResponseDto;

import java.util.Date;

import com.isigntech.Model.UserDetails;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class UserDetailsResponseDto {

	long userId;
	String fullName;
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

}
