package com.isigntech.mapper;

import com.isigntech.dto.ExpenseDto;
import com.isigntech.entity.Expense;

public class ExpenseMapper {

	public static ExpenseDto mapToExpenseDto(Expense expense){
		ExpenseDto expenseDto = new ExpenseDto(
				expense.getId(),
				expense.getPurchaseDate(),
				expense.getStoreName(),
				expense.getCategory(),
				expense.getItemDescription(),
				expense.getQuantity(),
				expense.getUnitPrice(),
				expense.getTotalAmount(),
				expense.getReceiptAttached(),
				expense.getReceiptFilePath(),
				expense.getReceiptReason()
        );
        return expenseDto;
    }

    public static Expense mapToExpense(ExpenseDto expenseDto){
    	Expense expense = new Expense(
    			expenseDto.getId(),
    			expenseDto.getPurchaseDate(),
    			expenseDto.getStoreName(),
    			expenseDto.getCategory(),
    			expenseDto.getItemDescription(),
    			expenseDto.getQuantity(),
    			expenseDto.getUnitPrice(),
    			expenseDto.getTotalAmount(),
    			expenseDto.getReceiptAttached(),
    			expenseDto.getReceiptFilePath(),
    			expenseDto.getReceiptReason()
        );
        return expense;
    }
	
}
