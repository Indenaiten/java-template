package com.codenaiten.template.server.app.feature.test.service;

import com.codenaiten.template.server.app.feature.test.TestMapper;
import com.codenaiten.template.server.app.feature.test.api.GetAllTestUseCase;
import com.codenaiten.template.server.app.feature.test.dto.TestInfo;
import com.codenaiten.template.server.app.feature.test.spi.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetAllTestService implements GetAllTestUseCase {

    private final TestRepository testRepository;

    private final TestMapper testMapper;

    @Override
    public List<TestInfo> run() {
        log.debug( "Finding all tests" );
        return this.testRepository.findAll().stream().map( this.testMapper::toDto ).toList();
    }
}
