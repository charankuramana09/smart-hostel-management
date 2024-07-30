package com.isigntech.service.impl;

import com.isigntech.dto.ExpenseDto;
import com.isigntech.entity.Expense;
import com.isigntech.mapper.ExpenseMapper;
import com.isigntech.repository.ExpenseRepository;
import com.isigntech.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public ExpenseDto createExpense(ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.mapToExpense(expenseDto);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.mapToExpenseDto(savedExpense);
    }

    @Override
    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        return ExpenseMapper.mapToExpenseDto(expense);
    }

    @Override
    public List<ExpenseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(ExpenseMapper::mapToExpenseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        
        existingExpense.setPurchaseDate(expenseDto.getPurchaseDate());
        existingExpense.setStoreName(expenseDto.getStoreName());
        existingExpense.setCategory(expenseDto.getCategory());
        existingExpense.setItemDescription(expenseDto.getItemDescription());
        existingExpense.setQuantity(expenseDto.getQuantity());
        existingExpense.setUnitPrice(expenseDto.getUnitPrice());
        existingExpense.setTotalAmount(expenseDto.getTotalAmount());
        existingExpense.setReceiptAttached(expenseDto.getReceiptAttached());
        existingExpense.setReceiptFilePath(expenseDto.getReceiptFilePath());
        existingExpense.setReceiptReason(expenseDto.getReceiptReason());

        Expense updatedExpense = expenseRepository.save(existingExpense);
        return ExpenseMapper.mapToExpenseDto(updatedExpense);
    }

    @Override
    public void deleteExpense(Long id) {
        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
        expenseRepository.delete(existingExpense);
    }
}
