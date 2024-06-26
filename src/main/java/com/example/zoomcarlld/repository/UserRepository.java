package com.example.zoomcarlld.repository;

import com.example.zoomcarlld.entity.Booking;
import com.example.zoomcarlld.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
}
