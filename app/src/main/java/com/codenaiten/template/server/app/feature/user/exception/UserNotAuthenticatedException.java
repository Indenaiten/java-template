package com.codenaiten.template.server.app.feature.user.exception;

public class UserNotAuthenticatedException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "User Authentication not found";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserNotAuthenticatedException(){
        super( DEFAULT_MESSAGE );
    }

    public UserNotAuthenticatedException(final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
