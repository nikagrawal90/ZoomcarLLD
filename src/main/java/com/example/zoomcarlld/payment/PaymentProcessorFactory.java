package com.example.zoomcarlld.payment;

import com.example.zoomcarlld.enums.PaymentMethods;

public class PaymentProcessorFactory {
    public static PaymentProcessor getPaymentProcessor(PaymentMethods paymentMethods) {
        return switch (paymentMethods) {
            case UPI -> new UpiProcessor();
            case NET_BANKING -> new NetBankingProcessor();
            default -> new CreditCardProcessor();
        };
    }
}
