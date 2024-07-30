package com.isigntech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isigntech.dto.EmployeeSalariesDto;
import com.isigntech.service.EmployeeSalariesService;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins ="/*")
public class EmployeeSalariesController {

    @Autowired
    private EmployeeSalariesService employeeSalariesService;

    @PostMapping
    public ResponseEntity<EmployeeSalariesDto> createEmployeeSalaries(@RequestBody EmployeeSalariesDto employeeSalariesDto) {
        EmployeeSalariesDto createdEmployeeSalaries = employeeSalariesService.createEmployeeSalaries(employeeSalariesDto);
        return ResponseEntity.ok(createdEmployeeSalaries);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeSalariesDto> getEmployeeSalariesById(@PathVariable Long empId) {
        EmployeeSalariesDto employeeSalaries = employeeSalariesService.getEmployeeSalariesById(empId);
        return ResponseEntity.ok(employeeSalaries);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeSalariesDto>> getAllEmployeeSalaries() {
        List<EmployeeSalariesDto> employeeSalaries = employeeSalariesService.getAllEmployeeSalaries();
        return ResponseEntity.ok(employeeSalaries);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<EmployeeSalariesDto> updateEmployeeSalaries(@PathVariable Long empId, @RequestBody EmployeeSalariesDto employeeSalariesDto) {
        EmployeeSalariesDto updatedEmployeeSalaries = employeeSalariesService.updateEmployeeSalaries(empId, employeeSalariesDto);
        return ResponseEntity.ok(updatedEmployeeSalaries);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<Void> deleteEmployeeSalaries(@PathVariable Long empId) {
        employeeSalariesService.deleteEmployeeSalaries(empId);
        return ResponseEntity.noContent().build();
    }
    
    
}
