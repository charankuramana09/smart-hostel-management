package com.isigntech.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.isigntech.entity.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ExpenseDTO {

	 private Long id;
	    private Date purchaseDate;
	    private String storeName;
	    private CategoryType categoryType;
	    private boolean receiptAttached;
	    private String receiptReason;
	    private MultipartFile receiptFile;
}
