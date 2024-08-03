package com.isigntech.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
	 private Long id;
	    private LocalDate purchaseDate;
	    private String storeName;
	    private String category;
	    private String itemDescription;
	    private Integer quantity;
	    private Double unitPrice;
	    private Double totalAmount;
	    private byte[] receiptAttached;

}
