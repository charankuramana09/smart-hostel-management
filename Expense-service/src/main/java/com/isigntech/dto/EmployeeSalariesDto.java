package com.isigntech.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalariesDto {

	  private String empName;
	    private String designation;
	    private Double salary;
	    private Date paymentDate;
	    private byte[] receiptAttached;
}
