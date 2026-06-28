package com.airline.flight.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;


@Component
@Data
@Entity
@Table( name = "airplanes" )
@NoArgsConstructor
@AllArgsConstructor
public class Airplane {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "model_number", nullable = false,  unique = true)
    private String modelNumber;

    @Column(name = "manufacturer",  nullable = false)
    private String manufacturer;

    @Column(name = "registration_number",  nullable = false)
    private String registrationNumber;

    @Column(name = "seat_capacity",  nullable = false)
    private int seatCapacity;

    @CreationTimestamp
    @Column(name = "created_at",  nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",  nullable = false)
    private LocalDateTime updatedAt;
}
