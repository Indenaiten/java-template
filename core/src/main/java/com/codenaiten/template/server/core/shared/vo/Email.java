package com.codenaiten.template.server.core.shared.vo;

import java.util.Objects;
import java.util.Optional;

public record Email( String value ) implements ValueObject<String>{

    /** Longitud mínima del correo electrónico */
    public static final int MIN_SIZE = 6;

    /** Longitud máxima del correo electrónico */
    public static final int MAX_SIZE = 256;

    /** Expresión regular que define el formato válido de un correo electrónico */
    public static final String FORMAT = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public Email( final String value ){
        this.value = Optional.ofNullable( value ).map( String::toLowerCase ).map( String::trim ).orElse( null );
        if( Objects.isNull( this.value )) throw new IllegalArgumentException( "Email value cannot be null" );
        if( !validateMinSize( this.value )) throw new IllegalArgumentException( "Email value minimum size is %d: { \"value\": \"%s\" }".formatted( MIN_SIZE, value ));
        if( !validateMaxSize( this.value ) ) throw new IllegalArgumentException( "Email value maximum size is %d: { \"value\": \"%s\" }".formatted( MAX_SIZE, value ));
        if( !validateFormat( this.value )) throw new IllegalArgumentException( "Email value format is invalid: { \"value\": \"%s\", \"regex\": \"%s\" }".formatted( value, FORMAT ));
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| VALIDATION |---------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public static boolean test( final String candidate ){
        return ValueObject.test( () -> new Email( candidate ));
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
