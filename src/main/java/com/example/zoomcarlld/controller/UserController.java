package com.example.zoomcarlld.controller;

import com.example.zoomcarlld.entity.Booking;
import com.example.zoomcarlld.entity.User;
import com.example.zoomcarlld.entity.Vehicle;
import com.example.zoomcarlld.exception.InsufficientPaymentException;
import com.example.zoomcarlld.exception.VehicleUnavailableException;
import com.example.zoomcarlld.model.BookingRequest;
import com.example.zoomcarlld.model.UserIdRequest;
import com.example.zoomcarlld.model.VehicleAvailablityRequest;
import com.example.zoomcarlld.service.BookingService;
import com.example.zoomcarlld.service.UserService;
import com.example.zoomcarlld.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/zoomcar")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/getBookings")
    public List<Booking> getBookings(@RequestBody UserIdRequest userIdRequest) {
        return bookingService.getBookings(userIdRequest.getUserId());
    }

    @PostMapping("/getActiveBooking")
    public Booking getActiveBooking(@RequestBody UserIdRequest userIdRequest) {
        return bookingService.getActiveBooking(userIdRequest.getUserId());
    }

    @PostMapping("/bookVehicle")
    public Booking bookVehicle(@RequestBody BookingRequest bookingRequest) throws InsufficientPaymentException, VehicleUnavailableException {
        return bookingService.bookVehicle(bookingRequest);
    }

    @PostMapping("/getAvailableVehicle")
    public List<Vehicle> getAvailableVehicles(@RequestBody VehicleAvailablityRequest vehicleAvailablityRequest) {
        return vehicleService.getAllAvailableVehicles(vehicleAvailablityRequest.getStartTime(), vehicleAvailablityRequest.getEndTime());
    }
}
