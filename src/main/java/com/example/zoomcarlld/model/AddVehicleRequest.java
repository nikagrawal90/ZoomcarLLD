package com.example.zoomcarlld.model;

import lombok.Data;

@Data
public class AddVehicleRequest {
    private String numberPlate;
    private Double distance;
    private String model;
    private Double basePricePerHour;
    private Long owner;
    private Boolean available;
}
