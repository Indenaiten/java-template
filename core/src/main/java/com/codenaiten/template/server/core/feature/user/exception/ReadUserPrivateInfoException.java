package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.feature.user.User;
import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

@Getter
public class ReadUserPrivateInfoException extends AppException{

    public static final String DEFAULT_MESSAGE = "User could not be read user private info";

 //--------------------------------------------------------------------------------------------------------------------\\

    private final User requester;
    private final User user;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public ReadUserPrivateInfoException( final User requester, final User user ){
        super( DEFAULT_MESSAGE );
        this.requester = requester;
        this.user = user;
    }

    public ReadUserPrivateInfoException( final User requester, final User user, final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
        this.requester = requester;
        this.user = user;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}