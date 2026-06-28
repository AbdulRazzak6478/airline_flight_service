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

@Data
@Builder
@Component
@Entity
@Table( name = "airports")
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column( name = "name", nullable = false)
    private String name;

    @Column( name = "code", nullable = false, unique = true)
    private String code;

    @Column( name = "address", nullable = false)
    private String address;

    @Column( name = "latitude", nullable = false)
    private  Double latitude;

    @Column( name = "longitude", nullable = false)
    private  Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @CreationTimestamp
    @Column( name= "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column( name= "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departingFlights  = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivingFlights = new ArrayList<>();

}
