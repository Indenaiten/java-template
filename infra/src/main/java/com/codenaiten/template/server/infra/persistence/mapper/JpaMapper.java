package com.codenaiten.template.server.infra.persistence.mapper;

import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper( componentModel = "spring",
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface JpaMapper {

    default <T> T unwrapOptional( final Optional<T> src ){
        return src.orElse( null );
    }

    default <T> Optional<T> toOptional( final T src ){
        return Optional.ofNullable( src );
    }
}
