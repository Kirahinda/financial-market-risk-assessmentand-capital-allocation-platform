package com.ytfa.fmrcap.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(
            EntityNotFoundException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(
            IllegalArgumentException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid value supplied for parameter: "
                        + exception.getName(),
                request
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleUnreadableMessage(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid or missing request body. Check your JSON format and data types.",
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(
            Exception exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected server error occurred.",
                request
        );
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request) {

        ApiErrorResponse errorResponse = new ApiErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(errorResponse);
    }
}
