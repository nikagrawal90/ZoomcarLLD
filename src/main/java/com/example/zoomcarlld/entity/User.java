package com.example.zoomcarlld.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String contactNo;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @JsonBackReference(value = "user-vehicles")
    private List<Vehicle> vehiclesOwned;

    private String accountNo;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-bookings")
    private List<Booking> bookings;
}
