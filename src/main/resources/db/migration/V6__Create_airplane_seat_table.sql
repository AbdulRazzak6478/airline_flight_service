

CREATE TYPE airplane_seat_class AS ENUM (
    'ECONOMY','PREMIUM_ECONOMY', 'BUSINESS', 'FIRST'
);

CREATE TYPE airplane_seat_type AS ENUM ( 'WINDOW', 'MIDDLE', 'AISLE' );

CREATE TABLE airplane_seats (

    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    airplane_id UUID NOT NULL,

    seat_number VARCHAR(20) NOT NULL ,

    seat_class airplane_seat_class NOT NULL ,

    seat_type airplane_seat_type NOT NULL ,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_airplane_seats_airplanes FOREIGN KEY (airplane_id) REFERENCES airplanes(id),

    CONSTRAINT uq_airplane_seat_number UNIQUE (airplane_id, seat_number)


);