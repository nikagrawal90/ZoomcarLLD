package com.example.zoomcarlld.entity;

import com.example.zoomcarlld.enums.PaymentMethods;
import com.example.zoomcarlld.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-bookings")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference(value = "vehicle-bookings")
    private Vehicle vehicle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long paymentId;
    private Double paymentAmount;
    @Enumerated(EnumType.STRING)
    @Column(name = "tripStatus")
    private Status tripStatus;
}
