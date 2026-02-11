package com.codenaiten.template.server.infra.web.rest.feature.message.api;

import com.codenaiten.template.server.infra.web.rest.feature.message.dto.input.CreateMessageInput;
import com.codenaiten.template.server.infra.web.rest.feature.message.dto.output.MessageInfoOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "/message" )
public interface MessageApi {

    @PostMapping
    ResponseEntity<Void> create( @RequestBody CreateMessageInput input );

    @GetMapping( "/id/{id}")
    ResponseEntity<MessageInfoOutput> findById( @PathVariable Long id );

    @GetMapping( "/all" )
    ResponseEntity<List<MessageInfoOutput>> findAll();
}
