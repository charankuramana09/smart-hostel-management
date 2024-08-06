package com.hostel.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.info("Received Data - userId: {}, userName: {}, phone: {}, amount: {}", userId, userName, phone, amount);

        PaymentLinkRequestDto paymentLinkRequestDto = new PaymentLinkRequestDto();
        paymentLinkRequestDto.setUserName(userName);
        paymentLinkRequestDto.setUserId(userId);
        paymentLinkRequestDto.setPhone(phone);
        paymentLinkRequestDto.setAmount(amount);

        String paymentLink = paymentGateway.createPaymentLink(paymentLinkRequestDto);

        PaymentDetails paymentResponse = new PaymentDetails();
        paymentResponse.setPaymentLink(paymentLink);
        paymentResponse.setUserId(userId);
        paymentResponse.setUserName(userName);  // Set userName
        paymentResponse.setPhone(phone);        // Set phone
        paymentResponse.setAmount(amount);      // Set amount
        paymentResponse.setStatus(PaymentStatus.PENDING); 
        paymentResponse.setPaymentDate(LocalDate.now());

        logger.info("Saving PaymentDetails: {}", paymentResponse);
        paymentRepository.save(paymentResponse);

        logger.info("Payment link created and saved: {}", paymentLink);
        return paymentLink;
    }

    public Map<String, String> getPaymentStatus(String paymentId, String userId) {
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
        
        Map<String,String> map=new HashMap();
        map.put("status", status.toString());
        if(status!=null) {
        map.put("paymentAmount", ""+paymentDetails.get().getAmount());
        }
        
        return map;
    }
    
    public List<PaymentLinkRequestDto> getAllPaymentDetails() {
        logger.info("Fetching all payment details");

        return paymentRepository.findAll().stream().map(payment -> {
            logger.info("Processing payment: " + payment); // Log the payment entity

            PaymentLinkRequestDto dto = new PaymentLinkRequestDto();
            dto.setUserId(payment.getUserId());
            dto.setUserName(payment.getUserName()); // Set actual value
            dto.setPhone(payment.getPhone()); // Set actual value
            dto.setAmount(payment.getAmount());
            dto.setPaymentDate(payment.getPaymentDate());

            logger.info("Mapped DTO: " + dto); // Log the DTO
            return dto;
        }).collect(Collectors.toList());
    }
}
