package com.codenaiten.template.server.core.shared.model.vo;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

public record Timestamp( LocalDateTime value ) implements ValueObject<LocalDateTime>{

//--------------------------------------------------------------------------------------------------------------------\\
//---| CONSTRUCTOR |--------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public Timestamp( final LocalDateTime value ){
        this.value = value;
        if( Objects.isNull( this.value )) throw new IllegalArgumentException( "Timestamp value cannot be null" );
        if( !validateNotFuture( this.value )) throw new IllegalArgumentException( "Timestamp value cannot be in the future: { \"value\": \"%s\" }".formatted( value ));
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| BUILDER |------------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public static Timestamp now(){
        return new Timestamp( LocalDateTime.now() );
    }

//--------------------------------------------------------------------------------------------------------------------\\
//---| VALIDATION |---------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    public static boolean test( final LocalDateTime candidate ){
        return ValueObject.test( () -> new Timestamp( candidate ));
    }

    public static boolean validateNotFuture( final LocalDateTime candidate ){
        return Optional.ofNullable( candidate )
                .map( value -> !value.isAfter( LocalDateTime.now() ))
                .orElse( false );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
