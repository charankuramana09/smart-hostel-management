package com.isigntech.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String firstName;
	private String lastName;
	private String gender;
	private Date joiningDate;
	private String purpose;
	private String roomSharing;
	private String frequency;
	private String userType;
	private long mobileNumber;
	private long alternateMobileNumber;
	private String email;
	@Lob
	@Column(name = "id_proof", columnDefinition = "LONGBLOB")
	private byte[] idProof;
	private boolean status;
	private double paidAmount;
	private double pendingAmount;
	private double advancePayment;
	private String hostelName;
	private Date paymentETA;
	private int roomNumber;
	private String roomType;

}
