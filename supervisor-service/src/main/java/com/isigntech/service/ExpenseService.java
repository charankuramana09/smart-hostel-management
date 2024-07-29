package com.isigntech.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isigntech.dto.ExpenseDTO;
import com.isigntech.entity.Expense;
import com.isigntech.exception.ResourceNotFoundException;
import com.isigntech.repository.ExpenseRepository;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ExpenseDTO createExpense(ExpenseDTO expenseDTO) {
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        expense = expenseRepository.save(expense);
        return modelMapper.map(expense, ExpenseDTO.class);
    }

    public List<ExpenseDTO> getAllExpenses() {
        return expenseRepository.findAll().stream()
            .map(expense -> modelMapper.map(expense, ExpenseDTO.class))
            .collect(Collectors.toList());
    }

    public ExpenseDTO getExpenseById(Long id) {
        return expenseRepository.findById(id)
            .map(expense -> modelMapper.map(expense, ExpenseDTO.class))
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
    }

    public ExpenseDTO updateExpense(Long id, ExpenseDTO expenseDTO) {
        Expense expense = expenseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found"));
        modelMapper.map(expenseDTO, expense);
        expense = expenseRepository.save(expense);
        return modelMapper.map(expense, ExpenseDTO.class);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
