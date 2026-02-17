package com.codenaiten.template.server.core.feature.user.vo;

import com.codenaiten.template.server.core.shared.entity.vo.ValueObject;

import java.util.Objects;
import java.util.Optional;

public record UserPassword( String value ) implements ValueObject<String>{

    /** Longitud mínima */
    public static final int MIN_SIZE = 8;

    /** Longitud máxima */
    public static final int MAX_SIZE = 256;

    /** Expresión regular que define el formato válido */
    public static final String FORMAT = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.@$!%*?&])[A-Za-z\\d.@$!%*?&]{8,}$";

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public UserPassword(final String value ){
        this.value = Optional.ofNullable( value ).map( String::trim ).orElse( null );
        if( Objects.isNull( this.value )) throw new IllegalArgumentException( "UserPassword value cannot be null" );
        if( !validateMinSize( this.value )) throw new IllegalArgumentException( "UserPassword value minimum size is %d: { \"value\": \"%s\" }".formatted( MIN_SIZE, value ));
        if( !validateMaxSize( this.value )) throw new IllegalArgumentException( "UserPassword value maximum size is %d: { \"value\": \"%s\" }".formatted( MAX_SIZE, value ));
        if( !validateFormat( this.value )) throw new IllegalArgumentException( "UserPassword value format is invalid: { \"value\": \"%s\", \"regex\": \"%s\" }".formatted( value, FORMAT ));
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| VALIDATION |---------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public static boolean test( final String candidate ){
        return ValueObject.test( () -> new UserPassword( candidate ));
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
