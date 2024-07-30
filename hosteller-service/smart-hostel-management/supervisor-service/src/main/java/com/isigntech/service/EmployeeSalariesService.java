package com.isigntech.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isigntech.dto.EmployeeSalariesDTO;
import com.isigntech.entity.EmployeeSalaries;
import com.isigntech.exception.ResourceNotFoundException;
import com.isigntech.repository.EmployeeSalariesRepository;

@Service
public class EmployeeSalariesService {
    @Autowired
    private EmployeeSalariesRepository employeeSalariesRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EmployeeSalariesDTO createEmployeeSalaries(EmployeeSalariesDTO employeeSalariesDTO) {
        EmployeeSalaries employeeSalaries = modelMapper.map(employeeSalariesDTO, EmployeeSalaries.class);
        employeeSalaries = employeeSalariesRepository.save(employeeSalaries);
        return modelMapper.map(employeeSalaries, EmployeeSalariesDTO.class);
    }

    public List<EmployeeSalariesDTO> getAllEmployeeSalaries() {
        return employeeSalariesRepository.findAll().stream()
            .map(employeeSalaries -> modelMapper.map(employeeSalaries, EmployeeSalariesDTO.class))
            .collect(Collectors.toList());
    }

    public EmployeeSalariesDTO getEmployeeSalariesById(Long id) {
        return employeeSalariesRepository.findById(id)
            .map(employeeSalaries -> modelMapper.map(employeeSalaries, EmployeeSalariesDTO.class))
            .orElseThrow(() -> new ResourceNotFoundException("EmployeeSalaries not found"));
    }

    public EmployeeSalariesDTO updateEmployeeSalaries(Long id, EmployeeSalariesDTO employeeSalariesDTO) {
        EmployeeSalaries employeeSalaries = employeeSalariesRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("EmployeeSalaries not found"));
        modelMapper.map(employeeSalariesDTO, employeeSalaries);
        employeeSalaries = employeeSalariesRepository.save(employeeSalaries);
        return modelMapper.map(employeeSalaries, EmployeeSalariesDTO.class);
    }

    public void deleteEmployeeSalaries(Long id) {
        employeeSalariesRepository.deleteById(id);
    }
}
