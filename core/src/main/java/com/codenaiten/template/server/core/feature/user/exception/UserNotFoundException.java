package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserNotFoundException extends AppException{

    public static final String DEFAULT_MESSAGE = "User not found";

//--------------------------------------------------------------------------------------------------------------------\\

    private final UUID id;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserNotFoundException( final UUID id ){
        super( DEFAULT_MESSAGE );
        this.id = id;
    }

    public UserNotFoundException( final UUID id, final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
        this.id = id;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}