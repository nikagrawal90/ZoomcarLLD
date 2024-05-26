package com.example.zoomcarlld.model;

import com.example.zoomcarlld.enums.PaymentMethods;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    private Long userId;
    private Long vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long paymentId;
    private PaymentMethods paymentMethod;
}
