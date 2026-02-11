package com.codenaiten.template.server.app.feature.user.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "User with ID \"%s\" not found";

//--------------------------------------------------------------------------------------------------------------------\\

    private final UUID id;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserNotFoundException( final UUID id ){
        super( DEFAULT_MESSAGE.formatted( id ));
        this.id = id;
    }

    public UserNotFoundException( final Throwable cause, final UUID id ){
        super( DEFAULT_MESSAGE.formatted( id ), cause );
        this.id = id;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
