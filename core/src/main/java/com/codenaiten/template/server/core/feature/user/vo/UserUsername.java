package com.codenaiten.template.server.core.feature.user.vo;


import com.codenaiten.template.server.core.shared.vo.ValueObject;

import java.util.Objects;
import java.util.Optional;

public record UserUsername( String value ) implements ValueObject<String>{

    /** Longitud mínima del nickname de un usuario */
    public static final int MIN_SIZE = 3;

    /** Longitud máxima del nickname de un usuario */
    public static final int MAX_SIZE = 25;

    /** Expresión regular que define el formato válido de un nickname de usuario */
    public static final String FORMAT = "^[a-z][a-z0-9_-]*$";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserUsername( final String value ){
        this.value = Optional.ofNullable( value ).map( String::toLowerCase ).map( String::trim ).orElse( null );
        if( Objects.isNull( this.value )) throw new IllegalArgumentException( "UserUsername value cannot be null" );
        if( !validateMinSize( this.value )) throw new IllegalArgumentException( "UserUsername value minimum size is %d: { \"value\": \"%s\" }".formatted( MIN_SIZE, value ));
        if( !validateMaxSize( this.value )) throw new IllegalArgumentException( "UserUsername value maximum size is %d: { \"value\": \"%s\" }".formatted( MAX_SIZE, value ));
        if( !validateFormat( this.value )) throw new IllegalArgumentException( "UserUsername value format is invalid: { \"value\": \"%s\", \"regex\": \"%s\" }".formatted( value, FORMAT ));
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| VALIDATION |---------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public static boolean test( final String candidate ){
        return ValueObject.test( () -> new UserUsername( candidate ));
    }

    public static boolean validateMinSize( final String candidate ){
        return Optional.ofNullable( candidate )
                .map( value -> value.length() >= MIN_SIZE )
                .orElse( false );
    }

    public static boolean validateMaxSize( final String candidate ){
        return Optional.ofNullable( candidate )
                .map( value -> value.length() <= MAX_SIZE )
                .orElse( false );
    }

    public static boolean validateFormat( final String candidate ){
        return Optional.ofNullable( candidate )
                .map( value -> value.matches( FORMAT ))
                .orElse( false );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
