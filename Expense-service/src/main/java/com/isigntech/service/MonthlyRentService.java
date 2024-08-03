package com.isigntech.service;

import java.util.List;

import com.isigntech.dto.MonthlyRentDto;
import com.isigntech.entity.MonthlyRent;

public interface MonthlyRentService  {

//	MonthlyRentDto createHostelPayment(MonthlyRentDto hostelPaymentDto);
//	MonthlyRentDto updateHostelPayment(Long id, MonthlyRentDto hostelPaymentDto);
//	MonthlyRentDto getHostelPaymentById(Long id);
//    void deleteHostelPayment(Long id);
//    List<MonthlyRentDto> getAllHostelPayments();
	 MonthlyRent saveMonthlyRent(MonthlyRentDto monthlyRentDTO);
}
