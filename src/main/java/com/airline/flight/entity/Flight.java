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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Entity
@Table( name = "flights")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column( name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column( name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column( name = "price", nullable = false)
    private BigDecimal price;
    @Column( name = "status", nullable = false)
    private String status;

    @Column( name = "boarding_gate", nullable = true)
    private String boardingGate;

    @CreationTimestamp
    @Column( name= "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column( name= "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
