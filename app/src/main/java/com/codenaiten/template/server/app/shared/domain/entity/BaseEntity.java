package com.codenaiten.template.server.app.shared.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public abstract class BaseEntity<T extends Serializable> implements Entity<T>{

    protected T id;

//--------------------------------------------------------------------------------------------------------------------\\
//---| TO STRING |----------------------------------------------------------------------------------------------------\\
//--------------------------------------------------------------------------------------------------------------------\\

    @Override
    public String toString() {
        return "Entity( %s )".formatted( String.valueOf( this.id ));
    }

//--------------------------------------------------------------------------------------------------------------------\\

}
