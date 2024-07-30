package com.isigntech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isigntech.dto.EmployeeSalariesDto;
import com.isigntech.entity.EmployeeSalaries;
import com.isigntech.mapper.EmployeeSalariesMapper;
import com.isigntech.repository.EmployeeSalariesRepo;
import com.isigntech.service.EmployeeSalariesService;

@Service
public class EmployeeSalariesServiceImpl implements EmployeeSalariesService {

    @Autowired
    private EmployeeSalariesRepo employeeSalariesRepository;

    @Override
    public EmployeeSalariesDto createEmployeeSalaries(EmployeeSalariesDto employeeSalariesDto) {
        EmployeeSalaries employeeSalaries = EmployeeSalariesMapper.mapToEmployeeSalaries(employeeSalariesDto);
        EmployeeSalaries savedEmployeeSalaries = employeeSalariesRepository.save(employeeSalaries);
        return EmployeeSalariesMapper.mapToEmployeeSalariesDto(savedEmployeeSalaries);
    }

    @Override
    public EmployeeSalariesDto getEmployeeSalariesById(Long empId) {
        EmployeeSalaries employeeSalaries = employeeSalariesRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee Salaries not found with empId: " + empId));
        return EmployeeSalariesMapper.mapToEmployeeSalariesDto(employeeSalaries);
    }

    @Override
    public List<EmployeeSalariesDto> getAllEmployeeSalaries() {
        List<EmployeeSalaries> employeeSalariesList = employeeSalariesRepository.findAll();
        return employeeSalariesList.stream()
                .map(EmployeeSalariesMapper::mapToEmployeeSalariesDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeSalariesDto updateEmployeeSalaries(Long empId, EmployeeSalariesDto employeeSalariesDto) {
        EmployeeSalaries existingEmployeeSalaries = employeeSalariesRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee Salaries not found with empId: " + empId));
        
        existingEmployeeSalaries.setEmpName(employeeSalariesDto.getEmpName());
        existingEmployeeSalaries.setSalary(employeeSalariesDto.getSalary());
        existingEmployeeSalaries.setSalaryDate(employeeSalariesDto.getSalaryDate());

        EmployeeSalaries updatedEmployeeSalaries = employeeSalariesRepository.save(existingEmployeeSalaries);
        return EmployeeSalariesMapper.mapToEmployeeSalariesDto(updatedEmployeeSalaries);
    }

    @Override
    public void deleteEmployeeSalaries(Long empId) {
        EmployeeSalaries existingEmployeeSalaries = employeeSalariesRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee Salaries not found with empId: " + empId));
        employeeSalariesRepository.delete(existingEmployeeSalaries);
    }
    
    
}
