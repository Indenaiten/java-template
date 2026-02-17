package com.codenaiten.template.server.core.shared.dto.query;

import java.util.Optional;

public record PageQuery( Integer number, Integer size ){

    public static final Integer DEFAULT_NUMBER = 0;
    public static final Integer DEFAULT_SIZE = 25;

    public Integer number(){
        return Optional.ofNullable( this.number ).orElse( DEFAULT_NUMBER );
    }
    public Integer size(){
        return Optional.ofNullable( this.size ).orElse( DEFAULT_SIZE );
    }
}
