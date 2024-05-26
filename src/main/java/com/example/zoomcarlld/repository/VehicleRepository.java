package com.example.zoomcarlld.repository;

import com.example.zoomcarlld.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT * FROM vehicle v " +
            "WHERE v.vehicle_id NOT IN (" +
            "    SELECT b.vehicle_id FROM booking b " +
            "    WHERE (b.start_time < ?2 AND b.end_time > ?1)" +
            ")", nativeQuery = true)
    List<Vehicle> getAllAvailableVehicles(LocalDateTime startTime, LocalDateTime endTime);
}
