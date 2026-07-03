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
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "cities")
@Builder
public class City {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "name", nullable = false,  unique = true)
    private String name;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;


    @CreationTimestamp
    @Column(name = "created_at",  nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at",  nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "city")
    private List<Airport> airports;
}
