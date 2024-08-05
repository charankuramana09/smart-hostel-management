package com.admin.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	@Lob
	@Column(name = "id_proof", columnDefinition = "LONGBLOB")
	byte[] idProof;
	boolean status;
	double paidAmount;
	double pendingAmount;
	double advancePayment;
	String hostelName;
	Date paymentETA;
	int roomNumber;
	String roomType;
	private String paymentStatus;

	

}
