

CREATE TYPE flight_status AS ENUM (
    'SCHEDULED',
    'BOARDING',
    'DEPARTED',
    'IN_AIR',
    'LANDED',
    'DELAYED',
    'CANCELLED'
);

ALTER TABLE flights ALTER COLUMN status TYPE flight_status USING status::flight_status;