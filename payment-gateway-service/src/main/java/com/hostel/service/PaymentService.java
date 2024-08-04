package com.hostel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.dto.PaymentLinkRequestDto;
import com.hostel.entity.PaymentDetails;
import com.hostel.entity.PaymentStatus;
import com.hostel.repository.PaymentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

//    @Autowired
//    public PaymentService(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
//        this.paymentGateway = paymentGateway;
//        this.paymentRepository = paymentRepository;
//    }

    public String createLink(String userId, String userName, String phone, double amount) {
        logger.info("Creating payment link for userId: {}", userId);

        PaymentLinkRequestDto paymentLinkRequestDto = new PaymentLinkRequestDto();
        paymentLinkRequestDto.setUserName(userName); // Use dynamic user name
        paymentLinkRequestDto.setUserId(userId); // Use dynamic user ID
        paymentLinkRequestDto.setPhone(phone); // Use dynamic phone number
        paymentLinkRequestDto.setAmount(amount); // Use dynamic amount

        String paymentLink = paymentGateway.createPaymentLink(paymentLinkRequestDto);

        PaymentDetails paymentResponse = new PaymentDetails();
        paymentResponse.setPaymentLink(paymentLink);
        paymentResponse.setUserId(userId);
        paymentRepository.save(paymentResponse);
        paymentResponse.setStatus(PaymentStatus.PENDING); 
        paymentResponse.setPaymentDate(LocalDate.now());

        logger.info("Payment link created and saved: {}", paymentLink);
        return paymentLink;
    }

    public PaymentStatus getPaymentStatus(String paymentId, String userId) {
        logger.info("Fetching payment status for paymentId: {}, userId: {}", paymentId, userId);

        Optional<PaymentDetails> paymentDetails = paymentRepository.findByUserId(userId);

        if (paymentDetails.isEmpty()) {
            logger.error("Payment not found for userId: {}", userId);
            throw new RuntimeException("Payment not found");
        }

        PaymentStatus status = paymentGateway.getStatus(paymentId, userId);

        PaymentDetails paymentResponse = paymentDetails.get();
        paymentResponse.setStatus(status);
        paymentResponse.setPaymentId(paymentId);
        paymentRepository.save(paymentResponse);

        logger.info("Payment status updated: {}", status);
        return status;
    }
    
    public List<PaymentLinkRequestDto> getAllPaymentDetails() {
        logger.info("Fetching all payment details");

        return paymentRepository.findAll().stream().map(payment -> {
            PaymentLinkRequestDto dto = new PaymentLinkRequestDto();
            dto.setUserId(payment.getUserId());
            dto.setUserName("dummyUserName"); // Set actual value if available
            dto.setPhone("dummyPhone"); // Set actual value if available
            dto.setAmount(payment.getAmount());
            dto.setPaymentDate(payment.getPaymentDate());
            return dto;
        }).collect(Collectors.toList());
    }
}
