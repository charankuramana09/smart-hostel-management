package com.isigntech.controller;

import com.isigntech.dto.ExpenseDto;
import com.isigntech.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins ="/*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
        ExpenseDto createdExpense = expenseService.createExpense(expenseDto);
        return ResponseEntity.ok(createdExpense);
    }

    @GetMapping("/findById/{id}")

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
        ExpenseDto expense = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @GetMapping("/findAll")

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/update/{id}")

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id, @RequestBody ExpenseDto expenseDto) {
        ExpenseDto updatedExpense = expenseService.updateExpense(id, expenseDto);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
