package com.codenaiten.template.server.app.feature.message.exception;

import lombok.Getter;

@Getter
public class InvalidMessageException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Invalid message";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public InvalidMessageException( final String message ){
        super( message );
    }

    public InvalidMessageException( final Throwable cause, final String message ){
        super( message, cause );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
