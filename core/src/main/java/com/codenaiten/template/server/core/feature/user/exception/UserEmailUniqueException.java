package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

@Getter
public class UserEmailUniqueException extends AppException{

    public static final String DEFAULT_MESSAGE = "User Email must be unique";

//--------------------------------------------------------------------------------------------------------------------\\

    private final String email;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserEmailUniqueException( final String email ){
        super( DEFAULT_MESSAGE );
        this.email = email;
    }

    public UserEmailUniqueException( final String email, final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
        this.email = email;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
