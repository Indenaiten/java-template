package com.codenaiten.template.server.app.feature.test.application.api;

import com.codenaiten.template.server.app.feature.test.application.dto.TestInfo;

import java.util.List;

public interface GetAllTestUseCase {

    List<TestInfo> run();
}
