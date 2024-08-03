package com.isigntech.service.impl;

import com.isigntech.dto.EmployeeSalariesDto;
import com.isigntech.entity.EmployeeSalaries;
import com.isigntech.repository.EmployeeSalariesRepository;
import com.isigntech.service.EmployeeSalariesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeSalariesServiceImpl implements EmployeeSalariesService {

    @Autowired
    private EmployeeSalariesRepository employeeSalariesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeSalariesDto createEmployee(EmployeeSalariesDto employeeSalariesDto) {
        // Convert DTO to entity
        EmployeeSalaries employee = modelMapper.map(employeeSalariesDto, EmployeeSalaries.class);
        // Save the entity
        EmployeeSalaries savedEmployee = employeeSalariesRepository.save(employee);
        // Convert saved entity back to DTO
        return modelMapper.map(savedEmployee, EmployeeSalariesDto.class);
    }

    @Override
    public List<EmployeeSalariesDto> getAllEmployees() {
        return employeeSalariesRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeSalariesDto.class))
                .collect(Collectors.toList());
    }
}
