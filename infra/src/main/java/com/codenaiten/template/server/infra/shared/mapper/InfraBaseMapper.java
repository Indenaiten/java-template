package com.codenaiten.template.server.infra.shared.mapper;

import java.util.Optional;

public interface InfraBaseMapper{

    default <T> T unwrapOptional( final Optional<T> src ){
        return src.orElse( null );
    }
}
