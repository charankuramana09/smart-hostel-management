package com.isigntech.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MonthlyRent {

	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	    private String hostelName;
	    private String ownerName;
	    
	    @Temporal(TemporalType.DATE)
	    private Date paidDate;

	    private Double paidAmount;

	    @Lob
	    @Column(columnDefinition = "BLOB")
	    private byte[] receiptFile;

	    private Double totalAmount;
}
