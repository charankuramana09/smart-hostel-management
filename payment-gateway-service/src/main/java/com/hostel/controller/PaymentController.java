package com.hostel.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.entity.PaymentStatus;
import com.hostel.service.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins ="/*")
@RequestMapping("/payment")
public class PaymentController {

    PaymentService paymentService;

//    @Autowired
//    PaymentController(PaymentService paymentService){
//        this.paymentService = paymentService;
//    }

    @PostMapping("/createLink")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String createPaymentLink(
            @RequestParam String userId,
            @RequestParam String userName,
            @RequestParam String phone,
            @RequestParam double amount) {
        return paymentService.createLink(userId, userName, phone, amount);
    }

    @GetMapping("/getPaymentStatus")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERADMIN', 'ROLE_SUPERVISOR','ROLE_USER')")
    public String getPaymentStatus(@RequestParam("paymentId") String paymentId, @RequestParam("userId") String userId){
        return (paymentService.getPaymentStatus(paymentId, userId)).toString();
    }
}