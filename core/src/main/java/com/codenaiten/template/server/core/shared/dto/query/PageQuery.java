package com.codenaiten.template.server.core.shared.dto.query;

import java.util.Optional;

public record PageQuery( Integer number, Integer size ){

    public Integer number(){
        return Optional.ofNullable( this.number ).orElse( 0 );
    }
    public Integer size(){
        return Optional.ofNullable( this.number ).orElse( 25 );
    }
}
