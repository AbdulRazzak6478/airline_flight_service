package com.airline.flight.entity;


import com.airline.flight.enums.SeatClass;
import com.airline.flight.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "airplane_seats")
public class AirplaneSeat {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column( name = "seat_number", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column( name = "seat_class", nullable = false)
    private SeatClass seatClass;

    @Enumerated(EnumType.STRING)
    @Column( name = "seat_type", nullable = false)
    private SeatType seatType;


    @CreationTimestamp
    @Column( name = "created_at",  nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column( name= "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "airplane_id",  nullable = false)
    private Airplane  airplane;
}
