package com.airline.flight.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
@Data
@Entity
@Table( name = "airplanes" )
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airplane {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "model_number", nullable = false,  unique = true)
    private String modelNumber;

    @Column(name = "manufacturer",  nullable = false)
    private String manufacturer;

    @Column(name = "registration_number",  nullable = false)
    private String registrationNumber;

    @Column(name = "seat_capacity",  nullable = false)
    private Integer seatCapacity;


    @CreationTimestamp
    @Column(name = "created_at",  nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",  nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "airplane")
    private List<Flight> flights;

    @OneToMany(mappedBy = "airplane")
    private List<AirplaneSeat> seats;
}
