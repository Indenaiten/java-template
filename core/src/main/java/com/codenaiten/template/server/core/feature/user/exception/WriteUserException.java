package com.codenaiten.template.server.core.feature.user.exception;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.shared.exception.AppException;
import lombok.Getter;

@Getter
public class WriteUserException extends AppException{

    public static final String DEFAULT_MESSAGE = "User could not be write user info";

 //--------------------------------------------------------------------------------------------------------------------\\

    private final User requester;
    private final User user;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public WriteUserException( final User requester, final User user ){
        super( DEFAULT_MESSAGE );
        this.requester = requester;
        this.user = user;
    }

    public WriteUserException( final User requester, final User user, final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
        this.requester = requester;
        this.user = user;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}