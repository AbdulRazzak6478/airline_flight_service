package com.airline.flight.constants;

public final class ApiRoutes {

    private ApiRoutes(){}

    public static final String ROOT = "/";

    public static final String API = "/api";
    public static final String API_V1 = API + "/v1";

    // Resources
    public static final String AIRPLANES = "/airplanes";
    public static final String AIRPLANE_ID = "/{airplaneId}";

    public static final String CITIES = "/cities";
    public static final String AIRPORTS = "/airports";
    public static final String FLIGHTS = "/flights";

    // Nested Resources
    public static final String AIRPLANE_SEATS = "/seats";

}
