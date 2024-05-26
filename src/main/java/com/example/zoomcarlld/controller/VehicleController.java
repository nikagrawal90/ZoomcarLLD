package com.example.zoomcarlld.controller;

import com.example.zoomcarlld.entity.Vehicle;
import com.example.zoomcarlld.model.AddVehicleRequest;
import com.example.zoomcarlld.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zoomcar/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@RequestBody AddVehicleRequest newVehicle) {
        return vehicleService.addVehicle(newVehicle);
    }
}
