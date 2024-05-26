package com.example.zoomcarlld.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "vehicle")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    @Column(unique = true)
    private String numberPlate;
    private Double distance;
    private String model;
    private Double basePricePerHour;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "vehicle-bookings")
    private List<Booking> bookings;
//    @ColumnDefault(value = "true")
//    private Boolean available = true;
}
