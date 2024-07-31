package com.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLinkRequestDto {
    private String userId;
    private String userName;
    private String phone;
    private double amount;
}