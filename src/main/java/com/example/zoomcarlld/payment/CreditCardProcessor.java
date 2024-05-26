package com.example.zoomcarlld.payment;

import com.example.zoomcarlld.enums.PaymentMethods;
import com.example.zoomcarlld.model.PaymentDetails;

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public PaymentDetails getPaymentDetails(Long paymentId) {
        // Some logic to fetch payment data from payment service
        return PaymentDetails.builder().paymentMethods(PaymentMethods.CREDIT_CARD).PaymentAmount((double) 100).paymentId(paymentId).build();
    }
}
