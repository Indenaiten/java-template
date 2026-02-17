package com.codenaiten.template.server.core.shared.command;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Data
@RequiredArgsConstructor
public class PageCommand {
    private final Integer page;
    private final Integer size;

    public int getPage(){
        return Optional.ofNullable( this.page ).orElse( 0 );
    }

    public int getSize(){
        return Optional.ofNullable( this.page ).orElse( 25 );
    }
}
