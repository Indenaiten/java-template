package com.codenaiten.template.server.app.feature.test.application.service;

import com.codenaiten.template.server.app.feature.test.application.TestMapper;
import com.codenaiten.template.server.app.feature.test.application.api.GetAllTestUseCase;
import com.codenaiten.template.server.app.feature.test.application.dto.TestInfo;
import com.codenaiten.template.server.app.feature.test.domain.spi.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllTestService implements GetAllTestUseCase {

    private final TestRepository testRepositoryPort;

    private final TestMapper testMapper;

    @Override
    public List<TestInfo> run() {
        log.debug( "Finding all tests" );
        return this.testRepositoryPort.findAll().stream().map( this.testMapper::toDto ).toList();
    }
}
