package com.codenaiten.template.server.core.feature.user.model.vo;

import com.codenaiten.template.server.core.shared.model.vo.ValueObject;

import java.util.Objects;
import java.util.Optional;

public record UserName( String value ) implements ValueObject<String>{

    /** Longitud mínima del nombre de un usuario */
    public static final int MIN_SIZE = 3;

    /** Longitud máxima del nombre de un usuario */
    public static final int MAX_SIZE = 50;

    /** Expresión regular que define el formato válido de un nombre de usuario */
    public static final String FORMAT = "^[A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+(?:[ '-][A-Za-zÁÉÍÓÚÜÑáéíóúüñ]+)*$";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserName( final String value ){
        this.value = Optional.ofNullable( value ).map( String::trim ).orElse( null );
        if( Objects.isNull( this.value )) throw new IllegalArgumentException( "UserName value cannot be null" );
        if( !validateMinSize( this.value )) throw new IllegalArgumentException( "UserName value minimum size is %d: { \"value\": \"%s\" }".formatted( MIN_SIZE, value ));
        if( !validateMaxSize( this.value )) throw new IllegalArgumentException( "UserName value maximum size is %d: { \"value\": \"%s\" }".formatted( MAX_SIZE, value ));
        if( !validateFormat( this.value )) throw new IllegalArgumentException( "UserName value format is invalid: { \"value\": \"%s\", \"regex\": \"%s\" }".formatted( value, FORMAT ));
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| VALIDATION |---------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public static boolean test( final String candidate ){
        return ValueObject.test( () -> new UserName( candidate ));
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
