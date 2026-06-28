

-- Enable UUID generation (PostgreSQL)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE airplanes (

                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                           model_number VARCHAR(100) NOT NULL UNIQUE,

                           manufacturer VARCHAR(100) NOT NULL,

                           registration_number VARCHAR(50) NOT NULL UNIQUE,

                           seat_capacity INTEGER NOT NULL CHECK (seat_capacity > 0),

                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_airplanes_model_number
    ON airplanes(model_number);

CREATE INDEX idx_airplanes_registration_number
    ON airplanes(registration_number);
