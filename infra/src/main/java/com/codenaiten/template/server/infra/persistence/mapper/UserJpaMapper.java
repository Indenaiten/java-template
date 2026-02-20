package com.codenaiten.template.server.infra.persistence.mapper;

import com.codenaiten.template.server.app.shared.mapper.OptionalMapper;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.infra.persistence.entity.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
         uses = { OptionalMapper.class },
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface UserJpaMapper {

    User toEntity( UserJpaEntity src );
    UserJpaEntity toJpa( User src );
}
