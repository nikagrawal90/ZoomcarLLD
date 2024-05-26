package com.example.zoomcarlld.model;

import com.example.zoomcarlld.enums.PaymentMethods;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaymentDetails {
    private Double PaymentAmount;
    private Long paymentId;
    private PaymentMethods paymentMethods;
}
