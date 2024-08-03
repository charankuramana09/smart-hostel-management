package com.isigntech.service.impl;

import com.isigntech.dto.ExpenseDto;
import com.isigntech.entity.Expense;
import com.isigntech.mapper.ExpenseMapper;
import com.isigntech.repository.ExpenseRepository;
import com.isigntech.service.ExpenseService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
    private ExpenseRepository expenseRepository;

	 @Autowired
	    private ModelMapper modelMapper;
	
	@Override
    public ExpenseDto saveExpense(ExpenseDto expenseDTO) {
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        Expense savedExpense = expenseRepository.save(expense);
        return modelMapper.map(savedExpense, ExpenseDto.class);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        return expenseRepository.findAll().stream()
                .map(expense -> modelMapper.map(expense, ExpenseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        return modelMapper.map(expense, ExpenseDto.class);
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

}
