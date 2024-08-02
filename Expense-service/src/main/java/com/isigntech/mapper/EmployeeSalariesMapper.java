package com.isigntech.mapper;

import com.isigntech.dto.EmployeeSalariesDto;
import com.isigntech.entity.EmployeeSalaries;

public class EmployeeSalariesMapper {

	
	public static EmployeeSalariesDto mapToEmployeeSalariesDto(EmployeeSalaries employeeSalaries){
		EmployeeSalariesDto employeeSalariesDto = new EmployeeSalariesDto(
				employeeSalaries.getEmpId(),
				employeeSalaries.getEmpName(),
				employeeSalaries.getDesignation(),
				employeeSalaries.getSalary(),
				employeeSalaries.getPaymentDate()
				
        );
        return employeeSalariesDto;
    }

    public static EmployeeSalaries mapToEmployeeSalaries(EmployeeSalariesDto employeeSalariesDto){
    	EmployeeSalaries employeeSalaries = new EmployeeSalaries(
    			employeeSalariesDto.getEmpId(),
    			employeeSalariesDto.getEmpName(),
    			employeeSalariesDto.getDesignation(),
    			employeeSalariesDto.getSalary(),
    			employeeSalariesDto.getPaymentDate()
    			
        );
        return employeeSalaries;
    }
}
