package com.codenaiten.template.server.app.feature.test;

import com.codenaiten.template.server.app.feature.test.dto.TestInfo;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface TestMapper {

    TestInfo toDto( Test test );
}
