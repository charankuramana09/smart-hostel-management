package com.isigntech.controller;

import com.isigntech.dto.ExpenseDto;
import com.isigntech.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin(origins ="/*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<ExpenseDto> createExpense(
            @RequestParam("purchaseDate") String purchaseDate,
            @RequestParam("storeName") String storeName,
            @RequestParam("category") String category,
            @RequestParam("itemDescription") String itemDescription,
            @RequestParam("quantity") Integer quantity,
            @RequestParam("unitPrice") Double unitPrice,
            @RequestParam("totalAmount") Double totalAmount,
            @RequestParam("receiptAttached") MultipartFile receiptAttached) throws IOException {

    	ExpenseDto expenseDTO = new ExpenseDto();
        expenseDTO.setPurchaseDate(LocalDate.parse(purchaseDate));
        expenseDTO.setStoreName(storeName);
        expenseDTO.setCategory(category);
        expenseDTO.setItemDescription(itemDescription);
        expenseDTO.setQuantity(quantity);
        expenseDTO.setUnitPrice(unitPrice);
        expenseDTO.setTotalAmount(totalAmount);
        expenseDTO.setReceiptAttached(receiptAttached.getBytes());

        ExpenseDto savedExpense = expenseService.saveExpense(expenseDTO);
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses() {
        List<ExpenseDto> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id) {
    	ExpenseDto expenseDTO = expenseService.getExpenseById(id);
        return ResponseEntity.ok(expenseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
