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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/monthly")
@CrossOrigin(origins ="/*")
public class MonthlyRentController {

    @Autowired
    private MonthlyRentService monthlyRentService;

    @PostMapping("/submit")
    public ResponseEntity<Map<String, String>> submitMonthlyRent(@ModelAttribute MonthlyRentDto monthlyRentDTO) {
        monthlyRentService.saveMonthlyRent(monthlyRentDTO);
        Map<String,String> map=new HashMap<String, String>();
        map.put("update", "Monthly Rent submitted successfully");
        return ResponseEntity.ok(map);
    }
}
