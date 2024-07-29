package com.isigntech.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isigntech.dto.ExpenseDetailsDTO;
import com.isigntech.entity.ExpenseDetails;
import com.isigntech.exception.ResourceNotFoundException;
import com.isigntech.repository.ExpenseDetailsRepository;

@Service
public class ExpenseDetailsService {
    @Autowired
    private ExpenseDetailsRepository expenseDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ExpenseDetailsDTO createExpenseDetails(ExpenseDetailsDTO expenseDetailsDTO) {
        ExpenseDetails expenseDetails = modelMapper.map(expenseDetailsDTO, ExpenseDetails.class);
        expenseDetails = expenseDetailsRepository.save(expenseDetails);
        return modelMapper.map(expenseDetails, ExpenseDetailsDTO.class);
    }

    public List<ExpenseDetailsDTO> getAllExpenseDetails() {
        return expenseDetailsRepository.findAll().stream()
            .map(expenseDetails -> modelMapper.map(expenseDetails, ExpenseDetailsDTO.class))
            .collect(Collectors.toList());
    }

    public ExpenseDetailsDTO getExpenseDetailsById(Long id) {
        return expenseDetailsRepository.findById(id)
            .map(expenseDetails -> modelMapper.map(expenseDetails, ExpenseDetailsDTO.class))
            .orElseThrow(() -> new ResourceNotFoundException("ExpenseDetails not found"));
    }

    public ExpenseDetailsDTO updateExpenseDetails(Long id, ExpenseDetailsDTO expenseDetailsDTO) {
        ExpenseDetails expenseDetails = expenseDetailsRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ExpenseDetails not found"));
        modelMapper.map(expenseDetailsDTO, expenseDetails);
        expenseDetails = expenseDetailsRepository.save(expenseDetails);
        return modelMapper.map(expenseDetails, ExpenseDetailsDTO.class);
    }

    public void deleteExpenseDetails(Long id) {
        expenseDetailsRepository.deleteById(id);
    }
}
