package com.codenaiten.template.server.core.shared.model.vo;

import java.io.Serializable;
import java.util.function.Supplier;

public interface ValueObject<T extends Serializable> extends Serializable {

//--------------------------------------------------------------------------------------------------------------------\\

    T value();

//--------------------------------------------------------------------------------------------------------------------\\
//---| DEFAULT METHODS |----------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    default boolean different( final Object obj ){
        return !this.equals( obj );
    }

    default String stringValue(){
        return String.valueOf( this.value() );
    }

    static <T> boolean test( final Supplier<T> supplier ) {
        try{
            supplier.get();
            return true;
        }
        catch( final Exception e ){
            return false;
        }
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
