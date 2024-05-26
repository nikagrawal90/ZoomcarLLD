package com.example.zoomcarlld.service;

import com.example.zoomcarlld.entity.Vehicle;
import com.example.zoomcarlld.model.AddVehicleRequest;
import com.example.zoomcarlld.repository.UserRepository;
import com.example.zoomcarlld.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Vehicle> getAllAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime) {
        List<Vehicle> vehicles = vehicleRepository.getAllAvailableVehicles(startTime, endTime);
        return vehicles;
    }

    public Vehicle addVehicle(AddVehicleRequest addVehicleRequest) {
        Vehicle newVehicle = Vehicle.builder()
                .numberPlate(addVehicleRequest.getNumberPlate())
                .distance(addVehicleRequest.getDistance())
                .owner(userRepository.getReferenceById(addVehicleRequest.getOwner()))
                .basePricePerHour(addVehicleRequest.getBasePricePerHour())
                .model(addVehicleRequest.getModel())
                .build();
        vehicleRepository.save(newVehicle);
        return newVehicle;
    }
}
