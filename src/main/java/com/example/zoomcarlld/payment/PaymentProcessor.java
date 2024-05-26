package com.example.zoomcarlld.payment;

import com.example.zoomcarlld.model.PaymentDetails;

public interface PaymentProcessor {
    PaymentDetails getPaymentDetails(Long paymentId);
}
