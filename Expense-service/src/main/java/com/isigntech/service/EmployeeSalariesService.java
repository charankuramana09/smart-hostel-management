package com.isigntech.service;

import java.util.List;

import com.isigntech.dto.EmployeeSalariesDto;

public interface EmployeeSalariesService {

	EmployeeSalariesDto createEmployeeSalaries(EmployeeSalariesDto employeeSalariesDto);
    EmployeeSalariesDto getEmployeeSalariesById(Long empId);
    List<EmployeeSalariesDto> getAllEmployeeSalaries();
    EmployeeSalariesDto updateEmployeeSalaries(Long empId, EmployeeSalariesDto employeeSalariesDto);
    void deleteEmployeeSalaries(Long empId);
}
