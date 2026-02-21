package com.codenaiten.template.server.app.shared.mapper;

import java.util.Optional;

public interface AppBaseMapper{

    default <T> T unwrapOptional( final Optional<T> src ){
        return src.orElse( null );
    }
}
