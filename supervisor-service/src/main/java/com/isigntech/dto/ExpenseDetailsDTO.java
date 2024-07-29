package com.isigntech.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ExpenseDetailsDTO {

	private Long id;
    private Long expenseId;
    private String itemDescription;
    private int quantity;
    private double unitPrice;
    private double totalAmount;
}
