package com.isigntech.mapper;

import com.isigntech.dto.ExpenseDto;
import com.isigntech.entity.Expense;

public class ExpenseMapper {

    public static ExpenseDto mapToExpenseDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setId(expense.getId());
        expenseDto.setPurchaseDate(expense.getPurchaseDate());
        expenseDto.setStoreName(expense.getStoreName());
        expenseDto.setCategory(expense.getCategory());
        expenseDto.setItemDescription(expense.getItemDescription());
        expenseDto.setQuantity(expense.getQuantity());
        expenseDto.setUnitPrice(expense.getUnitPrice());
        expenseDto.setTotalAmount(expense.getTotalAmount());
        expenseDto.setReceiptAttached(expense.getReceiptAttached());
        return expenseDto;
    }

    public static Expense mapToExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setId(expenseDto.getId());
        expense.setPurchaseDate(expenseDto.getPurchaseDate());
        expense.setStoreName(expenseDto.getStoreName());
        expense.setCategory(expenseDto.getCategory());
        expense.setItemDescription(expenseDto.getItemDescription());
        expense.setQuantity(expenseDto.getQuantity());
        expense.setUnitPrice(expenseDto.getUnitPrice());
        expense.setTotalAmount(expenseDto.getTotalAmount());
        expense.setReceiptAttached(expenseDto.getReceiptAttached());
        return expense;
    }
}
