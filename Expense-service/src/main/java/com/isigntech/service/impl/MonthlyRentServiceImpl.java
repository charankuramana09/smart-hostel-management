package com.isigntech.service.impl;

import com.isigntech.dto.MonthlyRentDto;
import com.isigntech.entity.MonthlyRent;
import com.isigntech.repository.MonthlyRentRepository;
import com.isigntech.service.MonthlyRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthlyRentServiceImpl implements MonthlyRentService {

    @Autowired
    private MonthlyRentRepository monthlyRentRepository;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public MonthlyRent saveMonthlyRent(MonthlyRentDto monthlyRentDTO) {
        MonthlyRent monthlyRent = new MonthlyRent();
        monthlyRent.setHostelName(monthlyRentDTO.getHostelName());
        monthlyRent.setOwnerName(monthlyRentDTO.getOwnerName());
        monthlyRent.setPaidDate(java.sql.Date.valueOf(monthlyRentDTO.getPaidDate()));
        monthlyRent.setPaidAmount(monthlyRentDTO.getPaidAmount());
        monthlyRent.setTotalAmount(monthlyRentDTO.getTotalAmount());

        if (monthlyRentDTO.getReceiptFile() != null && !monthlyRentDTO.getReceiptFile().isEmpty()) {
            try {
                byte[] receiptData = monthlyRentDTO.getReceiptFile().getBytes();
                monthlyRent.setReceiptFile(receiptData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return monthlyRentRepository.save(monthlyRent);
    }
    
}
