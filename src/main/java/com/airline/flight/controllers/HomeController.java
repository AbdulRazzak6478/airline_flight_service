package com.airline.flight.controllers;


import com.airline.flight.constants.ApiRoutes;
import com.airline.flight.dto.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping
    public ResponseEntity<ApiResponse<String>> root(){
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message("Successfully Running")
                .data("Working fine")
                .errors(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .path(ApiRoutes.ROOT)
                .traceId("Testing correlation id")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
