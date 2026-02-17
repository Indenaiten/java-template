package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

@Getter
public class UserUsernameUniqueException extends AppException{

    public static final String DEFAULT_MESSAGE = "User Username must be unique";

//--------------------------------------------------------------------------------------------------------------------\\

    private final String username;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserUsernameUniqueException( final String username ){
        super( DEFAULT_MESSAGE );
        this.username = username;
    }

    public UserUsernameUniqueException( final String username, final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
        this.username = username;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
