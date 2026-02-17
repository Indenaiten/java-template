package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

@Getter
public class UserAuthenticationNotFoundException extends AppException{

    public static final String DEFAULT_MESSAGE = "User authentication not found";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserAuthenticationNotFoundException(){
        super( DEFAULT_MESSAGE );
    }

    public UserAuthenticationNotFoundException( final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}