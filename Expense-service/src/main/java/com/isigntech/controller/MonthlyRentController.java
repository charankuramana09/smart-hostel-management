package com.isigntech.controller;

import com.isigntech.dto.MonthlyRentDto;
import com.isigntech.service.MonthlyRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/monthly")
public class MonthlyRentController {

    @Autowired
    private MonthlyRentService monthlyRentService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<MonthlyRentDto> createHostelPayment(@RequestParam("hostelName") String hostelName,
                                                              @RequestParam("ownerName") String ownerName,
                                                              @RequestParam("paidDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date paidDate,
                                                              @RequestParam("paidAmount") Double paidAmount,
                                                              @RequestParam("totalAmount") Double totalAmount,
                                                              @RequestParam("receiptFile") MultipartFile receiptFile) {
        MonthlyRentDto hostelPaymentDto = new MonthlyRentDto();
        hostelPaymentDto.setHostelName(hostelName);
        hostelPaymentDto.setOwnerName(ownerName);
        hostelPaymentDto.setPaidDate(paidDate);
        hostelPaymentDto.setPaidAmount(paidAmount);
        hostelPaymentDto.setTotalAmount(totalAmount);
        hostelPaymentDto.setReceiptFile(receiptFile);

        MonthlyRentDto createdHostelPayment = monthlyRentService.createHostelPayment(hostelPaymentDto);
        return ResponseEntity.ok(createdHostelPayment);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<MonthlyRentDto> updateHostelPayment(@PathVariable Long id,
                                                              @RequestParam("hostelName") String hostelName,
                                                              @RequestParam("ownerName") String ownerName,
                                                              @RequestParam("paidDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date paidDate,
                                                              @RequestParam("paidAmount") Double paidAmount,
                                                              @RequestParam("totalAmount") Double totalAmount,
                                                              @RequestParam("receiptFile") MultipartFile receiptFile) {
        MonthlyRentDto hostelPaymentDto = new MonthlyRentDto();
        hostelPaymentDto.setHostelName(hostelName);
        hostelPaymentDto.setOwnerName(ownerName);
        hostelPaymentDto.setPaidDate(paidDate);
        hostelPaymentDto.setPaidAmount(paidAmount);
        hostelPaymentDto.setTotalAmount(totalAmount);
        hostelPaymentDto.setReceiptFile(receiptFile);

        MonthlyRentDto updatedHostelPayment = monthlyRentService.updateHostelPayment(id, hostelPaymentDto);
        return ResponseEntity.ok(updatedHostelPayment);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<MonthlyRentDto> getHostelPaymentById(@PathVariable Long id) {
        MonthlyRentDto hostelPaymentDto = monthlyRentService.getHostelPaymentById(id);
        return ResponseEntity.ok(hostelPaymentDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<Void> deleteHostelPayment(@PathVariable Long id) {
        monthlyRentService.deleteHostelPayment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR')")
    public ResponseEntity<List<MonthlyRentDto>> getAllHostelPayments() {
        List<MonthlyRentDto> hostelPayments = monthlyRentService.getAllHostelPayments();
        return ResponseEntity.ok(hostelPayments);
    }
}
