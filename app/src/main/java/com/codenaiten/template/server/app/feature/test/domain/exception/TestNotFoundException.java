package com.codenaiten.template.server.app.feature.test.domain.exception;

import lombok.Getter;

@Getter
public class TestNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Test with ID \"%d\" not found";

//--------------------------------------------------------------------------------------------------------------------\\

    private final Long id;

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public TestNotFoundException( final Long id ){
        super( DEFAULT_MESSAGE.formatted( id ));
        this.id = id;
    }

    public TestNotFoundException( final Throwable cause, final Long id ){
        super( DEFAULT_MESSAGE.formatted( id ), cause );
        this.id = id;
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
