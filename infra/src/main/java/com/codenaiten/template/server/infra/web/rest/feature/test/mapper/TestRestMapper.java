package com.codenaiten.template.server.infra.web.rest.feature.test.mapper;

import com.codenaiten.template.server.app.feature.test.api.CreateTestUseCase;
import com.codenaiten.template.server.app.feature.test.dto.TestInfo;
import com.codenaiten.template.server.infra.web.rest.feature.test.dto.input.CreateTestInput;
import com.codenaiten.template.server.infra.web.rest.feature.test.dto.output.TestInfoOutput;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface TestRestMapper {
    CreateTestUseCase.Request toRequest( CreateTestInput src );
    TestInfoOutput toOutput( TestInfo src );
}
