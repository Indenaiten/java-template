package com.codenaiten.template.server.infra.persistence.mapper;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.infra.persistence.entity.UserJpaEntity;
import com.codenaiten.template.server.infra.shared.mapper.InfraBaseMapper;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface UserJpaMapper extends InfraBaseMapper{

    User toEntity( UserJpaEntity src );
    UserJpaEntity toJpa( User src );
}
