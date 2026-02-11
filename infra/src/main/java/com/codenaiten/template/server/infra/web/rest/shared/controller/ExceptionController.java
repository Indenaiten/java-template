package com.codenaiten.template.server.infra.web.rest.shared.controller;

import com.codenaiten.template.server.app.feature.message.exception.MessageNotFoundException;
import com.codenaiten.template.server.app.feature.user.exception.UserNotAuthenticatedException;
import com.codenaiten.template.server.app.feature.user.exception.UserNotFoundException;
import com.codenaiten.template.server.app.shared.exception.AppException;
import com.codenaiten.template.server.app.shared.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler( UserNotAuthenticatedException.class )
    public ResponseEntity<String> handleUserNotAuthenticatedException( final UserNotAuthenticatedException e ) {
        return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( e.getMessage() );
    }

    @ExceptionHandler( MessageNotFoundException.class )
    public ResponseEntity<String> handleMessageNotFoundException( final MessageNotFoundException e ) {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getMessage() );
    }

    @ExceptionHandler( UserNotFoundException.class )
    public ResponseEntity<String> handleUserNotFoundException( final UserNotFoundException e ) {
        return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( e.getMessage() );
    }

    @ExceptionHandler( ValidationException.class )
    public ResponseEntity<String> handleValidationExceptionException( final ValidationException e ) {
        final List<String> errors = e.getErrors();
        return ResponseEntity.badRequest().body( String.join(", ", errors ));
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
