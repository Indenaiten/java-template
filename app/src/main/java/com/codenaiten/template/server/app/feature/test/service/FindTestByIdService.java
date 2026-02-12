package com.codenaiten.template.server.app.feature.test.service;

import com.codenaiten.template.server.app.feature.test.Test;
import com.codenaiten.template.server.app.feature.test.TestMapper;
import com.codenaiten.template.server.app.feature.test.api.FindTestByIdUseCase;
import com.codenaiten.template.server.app.feature.test.dto.TestInfo;
import com.codenaiten.template.server.app.feature.test.exception.TestNotFoundException;
import com.codenaiten.template.server.app.feature.test.spi.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindTestByIdService implements FindTestByIdUseCase {

    private final TestRepository testRepository;

    private final TestMapper testMapper;

    @Override
    public TestInfo run(final Long id) {
        log.debug( "Finding test with id: {}", id );
        final Optional<Test> test = this.testRepository.findById( id );
        return test.map( this.testMapper::toDto ).orElseThrow( () -> new TestNotFoundException( id ));
    }
}
