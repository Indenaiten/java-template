package com.codenaiten.template.server.infra.web;

import com.codenaiten.template.server.core.shared.exception.AppException;
import com.codenaiten.template.server.api.shared.response.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorAdviceController{

    @ExceptionHandler( IllegalArgumentException.class )
    public ResponseEntity<RestResponse<Void>> handleIllegalArgumentException(final IllegalArgumentException ex ) {
        final RestResponse<Void> response = RestResponse.error().message( ex.getMessage() ).build();
        return ResponseEntity.badRequest().body( response );
    }

    @ExceptionHandler( AppException.class )
    public ResponseEntity<RestResponse<Void>> handleAppException( final AppException ex ) {
        final RestResponse<Void> response = RestResponse.error().message( ex.getMessage() ).build();
        return ResponseEntity.internalServerError().body( response );
    }

    @ExceptionHandler( Exception.class )
    public ResponseEntity<RestResponse<Void>> handleException( final Exception ex ) {
        final RestResponse<Void> response = RestResponse.error().build();
        return ResponseEntity.internalServerError().body( response );
    }
}
