package com.example.zoomcarlld.service;

import com.example.zoomcarlld.entity.Booking;
import com.example.zoomcarlld.entity.User;
import com.example.zoomcarlld.entity.Vehicle;
import com.example.zoomcarlld.enums.Status;
import com.example.zoomcarlld.exception.InsufficientPaymentException;
import com.example.zoomcarlld.exception.VehicleUnavailableException;
import com.example.zoomcarlld.model.BookingRequest;
import com.example.zoomcarlld.model.PaymentDetails;
import com.example.zoomcarlld.payment.PaymentProcessor;
import com.example.zoomcarlld.payment.PaymentProcessorFactory;
import com.example.zoomcarlld.repository.BookingRepository;
import com.example.zoomcarlld.repository.UserRepository;
import com.example.zoomcarlld.repository.VehicleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Booking> getBookings(Long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    public Booking getActiveBooking(Long userId) {
        return bookingRepository.getActiveBookingByUserId(userId);
    }

    public Booking bookVehicle(BookingRequest bookingRequest) throws InsufficientPaymentException, VehicleUnavailableException {
        Long vehicleId = bookingRequest.getVehicleId();
        Vehicle vehicle = vehicleRepository.getReferenceById(vehicleId);
        Double requiredPayment = (double) Duration.between(bookingRequest.getStartTime(), bookingRequest.getEndTime()).toHours()*vehicle.getBasePricePerHour();
        try {
            if(!checkVehicleAvailability(vehicle, bookingRequest.getStartTime(), bookingRequest.getEndTime())) {
                throw new VehicleUnavailableException("Vehicle Unavailable");
            }
        } catch (VehicleUnavailableException e) {
            log.error("Error occurred while validating payment details", e);
            throw e;
        }

        try {
            PaymentProcessor paymentProcessor = PaymentProcessorFactory.getPaymentProcessor(bookingRequest.getPaymentMethod());
            PaymentDetails paymentDetails = paymentProcessor.getPaymentDetails(bookingRequest.getPaymentId());
            if(paymentDetails.getPaymentAmount() < requiredPayment) {
                throw new InsufficientPaymentException(String.format("Payment not sufficient. Paid = %s, Required = %s", paymentDetails.getPaymentAmount(), requiredPayment));
            }
        } catch (InsufficientPaymentException e) {
            log.error(e);
            throw e;
        }

        Booking booking = Booking.builder().
                vehicle(vehicleRepository.getReferenceById(bookingRequest.getVehicleId())).
                startTime(bookingRequest.getStartTime()).
                endTime(bookingRequest.getEndTime()).
                paymentAmount(requiredPayment)
                .paymentId(bookingRequest.getPaymentId())
                .tripStatus(Status.PENDING)
                .user(userRepository.getReferenceById(bookingRequest.getUserId()))
                .build();

        bookingRepository.save(booking);

        sendPaymentToUser(vehicle.getOwner(), requiredPayment);

        return booking;
    }

    private void sendPaymentToUser(User owner, Double requiredPayment) {
        String accountId = owner.getAccountNo();
        log.info("Sending money to owner account {}", accountId);
        //Logic to send money to owner
    }

    private boolean checkVehicleAvailability(Vehicle vehicleRequested, LocalDateTime startTime, LocalDateTime endTime) {
        List<Booking> bookingsWithinTimeRange = bookingRepository.findAllBookingWithTimeRange(vehicleRequested.getVehicleId(), startTime, endTime);
        log.info(bookingsWithinTimeRange);
        return bookingsWithinTimeRange.isEmpty();
    }
}
