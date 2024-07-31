package com.isigntech.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "category")
    private String category;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;
    
    @Column(name = "total_amount")
    private Double totalAmount;

    @Column(name = "receipt_attached")
    private String receiptAttached;

    @Column(name = "receipt_file_path")
    private String receiptFilePath;

    @Column(name = "receipt_reason")
    private String receiptReason;

   
}
