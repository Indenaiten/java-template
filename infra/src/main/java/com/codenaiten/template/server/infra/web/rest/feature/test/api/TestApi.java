package com.codenaiten.template.server.infra.web.rest.feature.test.api;

import com.codenaiten.template.server.infra.web.rest.feature.test.dto.input.CreateTestInput;
import com.codenaiten.template.server.infra.web.rest.feature.test.dto.output.TestInfoOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "/test" )
public interface TestApi {

    @PostMapping
    ResponseEntity<Void> create( @RequestBody CreateTestInput input );

    @GetMapping( "/id/{id}")
    ResponseEntity<TestInfoOutput> findById( @PathVariable Long id );

    @GetMapping( "/all" )
    ResponseEntity<List<TestInfoOutput>> findAll();
}
