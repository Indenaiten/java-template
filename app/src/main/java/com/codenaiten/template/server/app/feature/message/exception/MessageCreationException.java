package com.codenaiten.template.server.app.feature.message.exception;

public class MessageCreationException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Message Creation Failed";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public MessageCreationException(){
        super( DEFAULT_MESSAGE );
    }

    public MessageCreationException(final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
