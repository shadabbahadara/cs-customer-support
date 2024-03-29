package com.callsign.customer.support.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Shadab Khan
 * @since 16/01/2022
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorResponse> incorrectPasswordExceptionHandler(TicketNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.TICKET_NOT_FOUND);
        errorResponse.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

