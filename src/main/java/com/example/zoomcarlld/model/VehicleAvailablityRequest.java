package com.example.zoomcarlld.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleAvailablityRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
