package com.hostel.service;

import java.time.LocalDate;
import java.time.ZoneId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostel.dto.PaymentLinkRequestDto;
import com.hostel.entity.PaymentStatus;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RazorPayGateway implements PaymentGateway {

    private final RazorpayClient razorpayClient;

//    @Autowired
//    public RazorPayGateway(RazorpayClient razorpayClient) {
//        this.razorpayClient = razorpayClient;
//    }

    @Override
    public String createPaymentLink(PaymentLinkRequestDto paymentLinkRequestDto) {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount", paymentLinkRequestDto.getAmount());
        paymentLinkRequest.put("currency", "INR");
        paymentLinkRequest.put("expire_by", LocalDate.now().plusDays(7).atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
        paymentLinkRequest.put("reference_id", paymentLinkRequestDto.getUserId());
        paymentLinkRequest.put("description", "Payment for order no " + paymentLinkRequestDto.getUserId());

        JSONObject customer = new JSONObject();
        customer.put("name", paymentLinkRequestDto.getUserName());
        customer.put("contact", paymentLinkRequestDto.getPhone());
        customer.put("email", "geekysanjay@gmail.com");
        paymentLinkRequest.put("customer", customer);

        JSONObject notes = new JSONObject();
        notes.put("policy_name", "Jeevan Bima");
        paymentLinkRequest.put("notes", notes);
        paymentLinkRequest.put("callback_url", "https://www.geekysanjay.com");
        paymentLinkRequest.put("callback_method", "get");

        try {
            PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url");
        } catch (RazorpayException e) {
            throw new RuntimeException("Failed to create payment link", e);
        }
    }

    @Override
    public PaymentStatus getStatus(String paymentId, String userId) {
        try {
            Payment payment = razorpayClient.payments.fetch(paymentId);
            String statusType = payment.get("status");
            return switch (statusType) {
                case "captured" -> PaymentStatus.SUCCESS;
                case "failed" -> PaymentStatus.FAILURE;
                default -> PaymentStatus.INITIATED;
            };
        } catch (RazorpayException e) {
            throw new RuntimeException("Unable to fetch the payment details", e);
        }
    }
}
