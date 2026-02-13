package com.codenaiten.template.server.infra.web.rest.feature.test;

import com.codenaiten.template.server.app.feature.test.application.api.CreateTestUseCase;
import com.codenaiten.template.server.app.feature.test.application.api.FindTestByIdUseCase;
import com.codenaiten.template.server.app.feature.test.application.api.GetAllTestUseCase;
import com.codenaiten.template.server.app.feature.test.application.dto.TestInfo;
import com.codenaiten.template.server.infra.web.rest.feature.test.api.TestApi;
import com.codenaiten.template.server.infra.web.rest.feature.test.dto.input.CreateTestInput;
import com.codenaiten.template.server.infra.web.rest.feature.test.dto.output.TestInfoOutput;
import com.codenaiten.template.server.infra.web.rest.feature.test.mapper.TestRestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController implements TestApi {

    private final TestRestMapper testMapper;

    private final CreateTestUseCase createTestUseCase;
    private final FindTestByIdUseCase findTestByIdUseCase;
    private final GetAllTestUseCase getAllTestUseCase;

    @Override
    public ResponseEntity<Void> create( final CreateTestInput input ) {
        this.createTestUseCase.run( this.testMapper.toRequest( input ));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TestInfoOutput> findById( final Long id ) {
        final TestInfo result = this.findTestByIdUseCase.run( id );
        return ResponseEntity.ok( this.testMapper.toOutput( result ));
    }

    @Override
    public ResponseEntity<List<TestInfoOutput>> findAll() {
        final List<TestInfo> result = this.getAllTestUseCase.run();
        return result.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok( result.stream().map( this.testMapper::toOutput ).toList());
    }
}
