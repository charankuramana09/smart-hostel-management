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

public class EmployeeSalariesDTO {

	
	private Long id;
    private Long expenseId;
    private String name;
    private double salary;
    private Date date;
}
