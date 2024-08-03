package com.isigntech.service;

import java.util.List;


import com.isigntech.dto.EmployeeSalariesDto;



public interface EmployeeSalariesService {

//	EmployeeSalariesDto saveEmployeeSalaries(EmployeeSalariesDto employeeSalariesDto, MultipartFile receiptFile) throws IOException;
//
//    EmployeeSalariesDto getEmployeeSalariesById(Long id);
//
//    List<EmployeeSalariesDto> getAllEmployeeSalaries();
//
//    void deleteEmployeeSalariesById(Long id);
    
    EmployeeSalariesDto createEmployee(EmployeeSalariesDto employeeDTO);
    List<EmployeeSalariesDto> getAllEmployees();
    
    
}
