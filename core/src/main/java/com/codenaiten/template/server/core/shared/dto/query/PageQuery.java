package com.codenaiten.template.server.core.shared.dto.query;

import java.util.Optional;

public record PageQuery( Integer page, Integer size){

    public Integer page(){
        return Optional.ofNullable( this.page ).orElse( 0 );
    }
    public Integer size(){
        return Optional.ofNullable( this.page ).orElse( 25 );
    }
}
