package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserIdUniqueException extends AppException{

    public static final String DEFAULT_MESSAGE = "User ID must be unique";

//--------------------------------------------------------------------------------------------------------------------\\

    private final UUID id;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserIdUniqueException( final UUID id ){
        super( DEFAULT_MESSAGE );
        this.id = id;
    }

    public UserIdUniqueException( final UUID id, final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
        this.id = id;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
