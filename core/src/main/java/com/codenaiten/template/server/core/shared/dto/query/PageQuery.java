package com.codenaiten.template.server.core.shared.dto.query;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public record PageQuery( Integer page, Integer size){

    public Integer page(){
        return Optional.ofNullable( this.page ).orElse( 0 );
    }
    public Integer size(){
        return Optional.ofNullable( this.page ).orElse( 25 );
    }
}
