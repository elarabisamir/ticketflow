package com.ticketflow.bookingservice.exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookingExceptionHandler {
    @ExceptionHandler(FeignException.NotFound.class)
    ProblemDetail eventNotFound(FeignException.NotFound exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                "The requested event does not exist.");
    }

    @ExceptionHandler(FeignException.class)
    ProblemDetail eventServiceUnavailable(FeignException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY,
                "Event Service could not complete the request.");
    }
}
