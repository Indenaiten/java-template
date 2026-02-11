package com.codenaiten.template.server.infra.persistence.mapper;

import com.codenaiten.template.server.app.feature.user.User;
import com.codenaiten.template.server.infra.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface UserJpaMapper {

    User toEntity( UserJpaEntity src );
    UserJpaEntity toJpa( User src );
}
