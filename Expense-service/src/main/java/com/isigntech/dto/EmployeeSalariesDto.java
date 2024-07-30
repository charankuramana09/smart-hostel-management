
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

	 private Long empId;
	 private String empName;
	 private Double salary;
	 private Date salaryDate;
}
