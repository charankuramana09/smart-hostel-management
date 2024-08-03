package com.isigntech.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isigntech.dto.EmployeeSalariesDto;
import com.isigntech.service.EmployeeSalariesService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeSalariesController {

    @Autowired
    private EmployeeSalariesService employeeSalariesService;

    @PostMapping
    public ResponseEntity<EmployeeSalariesDto> createEmployee(
            @RequestPart("employee") EmployeeSalariesDto employeeSalariesDto,
            @RequestPart(value = "receiptAttached", required = false) MultipartFile file) {
        try {
    
            if (file != null) {
                employeeSalariesDto.setReceiptAttached(file.getBytes());
            }

            EmployeeSalariesDto createdEmployee = employeeSalariesService.createEmployee(employeeSalariesDto);
            return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmployeeSalariesDto>> getAllEmployees() {
        List<EmployeeSalariesDto> employees = employeeSalariesService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
