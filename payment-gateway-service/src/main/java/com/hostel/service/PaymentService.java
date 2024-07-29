package com.hostel.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.dto.PaymentLinkRequestDto;
import com.hostel.entity.PaymentDetails;
import com.hostel.entity.PaymentStatus;
import com.hostel.repository.PaymentRepository;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    public String createLink(String orderId) {
        logger.info("Creating payment link for orderId: {}", orderId);

        PaymentLinkRequestDto paymentLinkRequestDto = new PaymentLinkRequestDto();
        paymentLinkRequestDto.setCustomerName("Sanjay");
        paymentLinkRequestDto.setOrderId(orderId);
        paymentLinkRequestDto.setPhone("8310206130");
        paymentLinkRequestDto.setAmount(100);

        String paymentLink = paymentGateway.createPaymentLink(paymentLinkRequestDto);

        PaymentDetails paymentResponse = new PaymentDetails();
        paymentResponse.setPaymentLink(paymentLink);
        paymentResponse.setOrderId(orderId);
        paymentRepository.save(paymentResponse);

        logger.info("Payment link created and saved: {}", paymentLink);
        return paymentLink;
    }

    public PaymentStatus getPaymentStatus(String paymentId, String orderId) {
        logger.info("Fetching payment status for paymentId: {}, orderId: {}", paymentId, orderId);

        Optional<PaymentDetails> paymentDetails = paymentRepository.findByOrderId(orderId);

        if (paymentDetails.isEmpty()) {
            logger.error("Payment not found for orderId: {}", orderId);
            throw new RuntimeException("Payment not found");
        }

        PaymentStatus status = paymentGateway.getStatus(paymentId, orderId);

        PaymentDetails paymentResponse = paymentDetails.get();
        paymentResponse.setStatus(status);
        paymentResponse.setPaymentId(paymentId);
        paymentRepository.save(paymentResponse);

        logger.info("Payment status updated: {}", status);
        return status;
    }
}
