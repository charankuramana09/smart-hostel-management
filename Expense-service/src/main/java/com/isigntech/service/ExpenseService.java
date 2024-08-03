package com.isigntech.service;

import java.util.List;

import com.isigntech.dto.ExpenseDto;

public interface ExpenseService {

	ExpenseDto saveExpense(ExpenseDto expenseDTO);
    List<ExpenseDto> getAllExpenses();
    ExpenseDto getExpenseById(Long id);
    void deleteExpense(Long id);
    
    
}
