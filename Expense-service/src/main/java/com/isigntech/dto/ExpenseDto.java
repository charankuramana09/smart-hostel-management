package com.isigntech.dto;

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
	private Date purchaseDate;
    private String storeName;
    private String category;
    private String itemDescription; // You can concatenate items if multiple
    private Integer quantity;
    private Double unitPrice;
    private Double totalAmount;
    private String receiptAttached;
    private String receiptFilePath;
    private String receiptReason;

}
