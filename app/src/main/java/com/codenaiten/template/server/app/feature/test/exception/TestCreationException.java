package com.codenaiten.template.server.app.feature.test.exception;

public class TestCreationException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Test Creation Failed";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public TestCreationException(){
        super( DEFAULT_MESSAGE );
    }

    public TestCreationException( final Throwable cause ){
        super( DEFAULT_MESSAGE, cause );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
