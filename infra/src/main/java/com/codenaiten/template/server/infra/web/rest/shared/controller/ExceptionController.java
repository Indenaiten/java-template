package com.codenaiten.template.server.infra.web.rest.shared.controller;

import com.codenaiten.template.server.app.feature.test.exception.TestNotFoundException;
import com.codenaiten.template.server.app.shared.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler( TestNotFoundException.class )
    public ResponseEntity<String> handleTestNotFoundException( final TestNotFoundException e ) {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getMessage() );
    }

    @ExceptionHandler( AppException.class )
    public ResponseEntity<String> handleAppException( final AppException e ) {
        return ResponseEntity.internalServerError().body( e.getMessage() );
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity<Void> handleException( final Exception e ) {
        return ResponseEntity.internalServerError().build();
    }
}
