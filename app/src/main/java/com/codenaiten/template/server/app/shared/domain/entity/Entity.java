package com.codenaiten.template.server.app.shared.domain.entity;

import java.io.Serializable;

public interface Entity<T extends Serializable> extends Serializable{

//--------------------------------------------------------------------------------------------------------------------\\

    T getId();

//--------------------------------------------------------------------------------------------------------------------\\

    Entity<T> copy();

//--------------------------------------------------------------------------------------------------------------------\\
//---| DEFAULT METHODS |----------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    default boolean different( final Object obj ){
        return !this.equals( obj );
    }

    default boolean is( final T id ){
        return this.getId().equals( id );
    }

    default boolean is( final Entity<? extends Serializable> entity ){
        return this.getId().equals( entity.getId() );
    }

    default boolean not( final T id ){
        return !this.is( id );
    }

    default boolean not( final Entity<? extends Serializable> entity ){
        return !this.is( entity );
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
