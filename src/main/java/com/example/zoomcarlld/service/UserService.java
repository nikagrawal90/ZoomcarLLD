package com.example.zoomcarlld.service;

import com.example.zoomcarlld.entity.Booking;
import com.example.zoomcarlld.entity.User;
import com.example.zoomcarlld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
