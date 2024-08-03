package com.isigntech.controller;

import com.isigntech.dto.MonthlyRentDto;
import com.isigntech.service.MonthlyRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/monthly")
@CrossOrigin(origins ="/*")
public class MonthlyRentController {

    @Autowired
    private MonthlyRentService monthlyRentService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitMonthlyRent(@ModelAttribute MonthlyRentDto monthlyRentDTO) {
        monthlyRentService.saveMonthlyRent(monthlyRentDTO);
        return new ResponseEntity<>("Monthly Rent submitted successfully", HttpStatus.CREATED);
    }
}
