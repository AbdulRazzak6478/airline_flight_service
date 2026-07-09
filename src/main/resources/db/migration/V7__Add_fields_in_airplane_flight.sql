


-- Add airport/city code
ALTER TABLE cities
    ADD COLUMN code VARCHAR(20);

-- Add available seats to flights
ALTER TABLE flights
    ADD COLUMN available_seats INTEGER NOT NULL DEFAULT 0;