package com.isigntech.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Expenses_form")
public class Expense {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private LocalDate purchaseDate;
    private String storeName;
    private String category;
    private String itemDescription;
    private Integer quantity;
    private Double unitPrice;
    private Double totalAmount;

    @Lob
    @Column(name = "receipt_attached", columnDefinition="LONGBLOB")
    private byte[] receiptAttached;
        
}
