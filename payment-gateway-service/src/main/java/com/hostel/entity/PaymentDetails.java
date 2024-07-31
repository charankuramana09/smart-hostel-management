package com.hostel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentDetails extends BaseModel{
    private String userId;
    private String paymentId;
    private String PaymentLink;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private double amount;
}
