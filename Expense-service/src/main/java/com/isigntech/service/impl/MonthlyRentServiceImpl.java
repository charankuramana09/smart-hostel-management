package com.isigntech.service.impl;

import com.isigntech.dto.MonthlyRentDto;
import com.isigntech.entity.MonthlyRent;
import com.isigntech.mapper.MonthlyRentMapper;
import com.isigntech.repository.MonthlyRentRepository;
import com.isigntech.service.MonthlyRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonthlyRentServiceImpl implements MonthlyRentService {

    @Autowired
    private MonthlyRentRepository monthlyRentRepository;

    @Autowired
    private MonthlyRentMapper monthlyRentMapper;

    @Override
    public MonthlyRentDto createHostelPayment(MonthlyRentDto hostelPaymentDto) {
        MonthlyRent monthlyRent = monthlyRentMapper.mapToMonthlyRent(hostelPaymentDto);
        storeFile(hostelPaymentDto.getReceiptFile());
        monthlyRent = monthlyRentRepository.save(monthlyRent);
        return monthlyRentMapper.mapToMonthlyRentDto(monthlyRent);
    }

    @Override
    public MonthlyRentDto updateHostelPayment(Long id, MonthlyRentDto hostelPaymentDto) {
        MonthlyRent monthlyRent = monthlyRentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel payment not found"));
        monthlyRent.setHostelName(hostelPaymentDto.getHostelName());
        monthlyRent.setOwnerName(hostelPaymentDto.getOwnerName());
        monthlyRent.setPaidDate(hostelPaymentDto.getPaidDate());
        monthlyRent.setPaidAmount(hostelPaymentDto.getPaidAmount());
        monthlyRent.setTotalAmount(hostelPaymentDto.getTotalAmount());
        if (hostelPaymentDto.getReceiptFile() != null && !hostelPaymentDto.getReceiptFile().isEmpty()) {
            storeFile(hostelPaymentDto.getReceiptFile());
            monthlyRent.setReceiptAttached(hostelPaymentDto.getReceiptFile().getOriginalFilename());
        }
        monthlyRent = monthlyRentRepository.save(monthlyRent);
        return monthlyRentMapper.mapToMonthlyRentDto(monthlyRent);
    }

    @Override
    public MonthlyRentDto getHostelPaymentById(Long id) {
        MonthlyRent monthlyRent = monthlyRentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel payment not found"));
        return monthlyRentMapper.mapToMonthlyRentDto(monthlyRent);
    }

    @Override
    public void deleteHostelPayment(Long id) {
        monthlyRentRepository.deleteById(id);
    }

    @Override
    public List<MonthlyRentDto> getAllHostelPayments() {
        List<MonthlyRent> hostelPayments = monthlyRentRepository.findAll();
        return hostelPayments.stream().map(monthlyRentMapper::mapToMonthlyRentDto).collect(Collectors.toList());
    }

    private void storeFile(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String uploadDir = "uploads/";
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                file.transferTo(new File(uploadDir + file.getOriginalFilename()));
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file", e);
            }
        }
    }
}
