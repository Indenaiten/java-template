package com.codenaiten.template.server.app.shared.mapper;

import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface OptionalMapper{

    default <T> T unwrap( Optional<T> src ){
        return src.orElse( null );
    }
}
