package com.codenaiten.template.server.infra.persistence.mapper;

import com.codenaiten.template.server.app.feature.test.domain.Test;
import com.codenaiten.template.server.infra.persistence.entity.TestJpaEntity;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface TestJpaMapper {

    Test toEntity( TestJpaEntity src );
    TestJpaEntity toJpa( Test src );
}
