package com.example.zoomcarlld.repository;

import com.example.zoomcarlld.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "Select * from booking where user_id=?1", nativeQuery = true)
    List<Booking> findAllByUserId(Long userId);

    @Query(value = "Select * from booking where trip_status='ACTIVE' and user_id=?1", nativeQuery = true)
    Booking getActiveBookingByUserId(Long userId);

    @Query(value = "Select * from booking where trip_status != 'COMPLETED' and vehicle_id=?1 and (end_time>=?2 and start_time<=?3)", nativeQuery = true)
    List<Booking> findAllBookingWithTimeRange(Long vehicleId, LocalDateTime startTime, LocalDateTime endTime);
}
