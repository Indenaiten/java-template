package com.codenaiten.template.server.app.feature.test.application;

import com.codenaiten.template.server.app.feature.test.domain.Test;
import com.codenaiten.template.server.app.feature.test.application.dto.TestInfo;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface TestMapper {

    TestInfo toDto( Test test );
}
