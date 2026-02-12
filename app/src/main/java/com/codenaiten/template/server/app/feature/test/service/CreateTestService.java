package com.codenaiten.template.server.app.feature.test.service;

import com.codenaiten.template.server.app.feature.test.Test;
import com.codenaiten.template.server.app.feature.test.api.CreateTestUseCase;
import com.codenaiten.template.server.app.feature.test.exception.TestCreationException;
import com.codenaiten.template.server.app.feature.test.spi.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTestService implements CreateTestUseCase {

    private final TestRepository testRepository;

    @Override
    @Transactional
    public void run(final Request request ){
        log.debug( "Creating test with message: {}", request.message() );
        final Test test = Test.builder().message( request.message() ).build();
        try{
            this.testRepository.save( test );
        }catch( final Exception e ){
            log.error( "Failed to create test", e );
            throw new TestCreationException( e );
        }
    }
}
