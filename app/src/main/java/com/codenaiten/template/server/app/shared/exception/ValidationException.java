package com.codenaiten.template.server.app.shared.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends AppException {

    public static final String DEFAULT_MESSAGE = "Validation Error";

//--------------------------------------------------------------------------------------------------------------------\\

    private final List<String> errors;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public ValidationException( final List<String> errors ){
        super( DEFAULT_MESSAGE );
        this.errors = errors;
    }

    public ValidationException( final Throwable cause, final List<String> errors ){
        super( cause, DEFAULT_MESSAGE );
        this.errors = errors;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
