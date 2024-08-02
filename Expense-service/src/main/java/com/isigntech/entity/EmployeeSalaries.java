package com.isigntech.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeSalaries {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long empId;

	    private String empName;
	    
	    private String designation;

	    private Double salary;

	    private Date paymentDate;
}
