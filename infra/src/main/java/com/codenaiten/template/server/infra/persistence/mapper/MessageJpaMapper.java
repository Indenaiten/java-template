package com.codenaiten.template.server.infra.persistence.mapper;

import com.codenaiten.template.server.app.feature.message.Message;
import com.codenaiten.template.server.infra.persistence.entity.MessageJpaEntity;
import com.codenaiten.template.server.infra.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;
import java.util.UUID;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface MessageJpaMapper {

    @Mapping( target = "owner", source = "owner.id" )
    Message toEntity( MessageJpaEntity src );

    @Mapping( target = "owner.id", source = "owner" )
    MessageJpaEntity toJpa( Message src );

    default UserJpaEntity toJpa( final UUID src ){
        return Optional.ofNullable( src ).map( id -> UserJpaEntity.builder().id( id ).build() ).orElse( null );
    }
}
