package com.hostel.service;

import org.springframework.stereotype.Component;

import com.hostel.dto.PaymentLinkRequestDto;
import com.hostel.entity.PaymentStatus;

@Component
public interface PaymentGateway {
    String createPaymentLink(PaymentLinkRequestDto paymentLinkRequestDto);
    PaymentStatus getStatus(String paymentId, String orderId);
}
