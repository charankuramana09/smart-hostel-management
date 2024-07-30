package com.admin.dto;

import java.util.Date;


import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailsDTO {
	
	long userId;
	String fullName;
    String gender;
    Date joiningDate;
	String  purpose ;
	String roomSharing;
	String frequency;
	String userType;
	long mobileNumber;
	long alternateMobileNumber;
	String email;
	
	
	 @Lob
	 byte[] idProof ;
	 boolean status ;
	double paidAmount;
	double pendingAmount;


}
