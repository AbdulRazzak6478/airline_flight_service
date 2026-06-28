

CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE flights (
      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

      flight_number VARCHAR(20) NOT NULL UNIQUE,

      airplane_id UUID NOT NULL,
      departure_airport_id UUID NOT NULL,
      arrival_airport_id UUID NOT NULL,

      departure_time TIMESTAMPTZ NOT NULL,
      arrival_time TIMESTAMPTZ NOT NULL,

      price DECIMAL(10,2) NOT NULL,

      status VARCHAR(30) NOT NULL,

      boarding_gate VARCHAR(20),

      created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

      CONSTRAINT fk_flights_airplanes
           FOREIGN KEY (airplane_id)
               REFERENCES airplanes(id),

      CONSTRAINT fk_flights_departure_airports
           FOREIGN KEY (departure_airport_id)
               REFERENCES airports(id),

      CONSTRAINT fk_flights_arrival_airports
           FOREIGN KEY (arrival_airport_id)
                REFERENCES airports(id),

      CONSTRAINT chk_flight_airports
           CHECK (departure_airport_id <> arrival_airport_id),

      CONSTRAINT chk_flight_price
           CHECK (price >= 0),

      CONSTRAINT chk_arrival_after_departure
           CHECK (arrival_time > departure_time)
);