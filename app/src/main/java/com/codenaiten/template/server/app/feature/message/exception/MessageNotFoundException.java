package com.codenaiten.template.server.app.feature.message.exception;

import lombok.Getter;

@Getter
public class MessageNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Message with ID \"%d\" not found";

//--------------------------------------------------------------------------------------------------------------------\\

    private final Long id;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public MessageNotFoundException( final Long id ){
        super( DEFAULT_MESSAGE.formatted( id ));
        this.id = id;
    }

    public MessageNotFoundException( final Throwable cause, final Long id ){
        super( DEFAULT_MESSAGE.formatted( id ), cause );
        this.id = id;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
