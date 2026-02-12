package com.codenaiten.template.server.app.shared.exception;

public class AppException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Unexpected Error";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public AppException( final String message ){
        super( message );
    }

    public AppException(){
        super( DEFAULT_MESSAGE );
    }

    public AppException( final Throwable cause, final String message ){
        super( message, cause );
    }

    public AppException( final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
