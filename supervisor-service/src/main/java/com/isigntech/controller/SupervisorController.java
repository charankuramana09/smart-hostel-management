package com.isigntech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isigntech.dto.EmployeeSalariesDTO;
import com.isigntech.dto.ExpenseDTO;
import com.isigntech.dto.ExpenseDetailsDTO;
import com.isigntech.service.EmployeeSalariesService;
import com.isigntech.service.ExpenseDetailsService;
import com.isigntech.service.ExpenseService;

@RestController
@RequestMapping("/api/supervisor")
public class SupervisorController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseDetailsService expenseDetailsService;

    @Autowired
    private EmployeeSalariesService employeeSalariesService;

    // Expense endpoints
    @PostMapping("/expenses")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.createExpense(expenseDTO));
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDTO));
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    // ExpenseDetails endpoints
    @PostMapping("/expense-details")
    public ResponseEntity<ExpenseDetailsDTO> createExpenseDetails(@RequestBody ExpenseDetailsDTO expenseDetailsDTO) {
        return ResponseEntity.ok(expenseDetailsService.createExpenseDetails(expenseDetailsDTO));
    }

    @GetMapping("/expense-details")
    public ResponseEntity<List<ExpenseDetailsDTO>> getAllExpenseDetails() {
        return ResponseEntity.ok(expenseDetailsService.getAllExpenseDetails());
    }

    @GetMapping("/expense-details/{id}")
    public ResponseEntity<ExpenseDetailsDTO> getExpenseDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseDetailsService.getExpenseDetailsById(id));
    }

    @PutMapping("/expense-details/{id}")
    public ResponseEntity<ExpenseDetailsDTO> updateExpenseDetails(@PathVariable Long id, @RequestBody ExpenseDetailsDTO expenseDetailsDTO) {
        return ResponseEntity.ok(expenseDetailsService.updateExpenseDetails(id, expenseDetailsDTO));
    }

    @DeleteMapping("/expense-details/{id}")
    public ResponseEntity<Void> deleteExpenseDetails(@PathVariable Long id) {
        expenseDetailsService.deleteExpenseDetails(id);
        return ResponseEntity.noContent().build();
    }

    // EmployeeSalaries endpoints
    @PostMapping("/employee-salaries")
    public ResponseEntity<EmployeeSalariesDTO> createEmployeeSalaries(@RequestBody EmployeeSalariesDTO employeeSalariesDTO) {
        return ResponseEntity.ok(employeeSalariesService.createEmployeeSalaries(employeeSalariesDTO));
    }

    @GetMapping("/employee-salaries")
    public ResponseEntity<List<EmployeeSalariesDTO>> getAllEmployeeSalaries() {
        return ResponseEntity.ok(employeeSalariesService.getAllEmployeeSalaries());
    }

    @GetMapping("/employee-salaries/{id}")
    public ResponseEntity<EmployeeSalariesDTO> getEmployeeSalariesById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeSalariesService.getEmployeeSalariesById(id));
    }

    @PutMapping("/employee-salaries/{id}")
    public ResponseEntity<EmployeeSalariesDTO> updateEmployeeSalaries(@PathVariable Long id, @RequestBody EmployeeSalariesDTO employeeSalariesDTO) {
        return ResponseEntity.ok(employeeSalariesService.updateEmployeeSalaries(id, employeeSalariesDTO));
    }

    @DeleteMapping("/employee-salaries/{id}")
    public ResponseEntity<Void> deleteEmployeeSalaries(@PathVariable Long id) {
        employeeSalariesService.deleteEmployeeSalaries(id);
        return ResponseEntity.noContent().build();
    }
}

