package com.airline.flight.exception;

import com.airline.flight.dto.common.ApiResponse;
import com.airline.flight.dto.common.ApiResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<List<String>>> handleGenricException(
            Exception ex,
            HttpServletRequest request
    ) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    ApiResponseBuilder.failed(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Something went wrong, try Again",
                            List.of(ex.getMessage()),
                            request.getRequestURI()
                    )
                );
    }

    // Duplicate Resource Exception
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleDuplicateResourceException(
            DuplicateResourceException ex,
            HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponseBuilder.failed(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Duplicate Resource",
                        List.of(ex.getMessage()),
                        request.getRequestURI()
                )
        );
    }

    // Validation fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<String>>> handleGenricException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponseBuilder.failed(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Validation Failed",
                                errors,
                                request.getRequestURI()
                        )
                );
    }
}
