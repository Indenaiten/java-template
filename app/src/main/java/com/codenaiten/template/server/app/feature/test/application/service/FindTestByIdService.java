package com.codenaiten.template.server.app.feature.test.application.service;

import com.codenaiten.template.server.app.feature.test.domain.Test;
import com.codenaiten.template.server.app.feature.test.application.TestMapper;
import com.codenaiten.template.server.app.feature.test.application.api.FindTestByIdUseCase;
import com.codenaiten.template.server.app.feature.test.application.dto.TestInfo;
import com.codenaiten.template.server.app.feature.test.domain.exception.TestNotFoundException;
import com.codenaiten.template.server.app.feature.test.domain.spi.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindTestByIdService implements FindTestByIdUseCase {

    private final TestRepository testRepositoryPort;

    private final TestMapper testMapper;

    @Override
    public TestInfo run(final Long id) {
        log.debug( "Finding test with id: {}", id );
        final Optional<Test> test = this.testRepositoryPort.findById( id );
        return test.map( this.testMapper::toDto ).orElseThrow( () -> new TestNotFoundException( id ));
    }
}
