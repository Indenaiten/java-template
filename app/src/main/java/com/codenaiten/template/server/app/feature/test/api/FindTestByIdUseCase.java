package com.codenaiten.template.server.app.feature.test.api;

import com.codenaiten.template.server.app.feature.test.dto.TestInfo;

public interface FindTestByIdUseCase {

    TestInfo run( Long id );
}
