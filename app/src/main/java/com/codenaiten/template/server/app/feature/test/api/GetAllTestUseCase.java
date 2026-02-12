package com.codenaiten.template.server.app.feature.test.api;

import com.codenaiten.template.server.app.feature.test.dto.TestInfo;

import java.util.List;

public interface GetAllTestUseCase {

    List<TestInfo> run();
}
