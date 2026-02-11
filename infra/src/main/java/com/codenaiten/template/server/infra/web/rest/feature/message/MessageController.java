package com.codenaiten.template.server.infra.web.rest.feature.message;

import com.codenaiten.template.server.app.feature.message.api.MessageService;
import com.codenaiten.template.server.app.feature.message.dto.MessageInfo;
import com.codenaiten.template.server.infra.web.rest.feature.message.api.MessageApi;
import com.codenaiten.template.server.infra.web.rest.feature.message.dto.input.CreateMessageInput;
import com.codenaiten.template.server.infra.web.rest.feature.message.dto.output.MessageInfoOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController implements MessageApi {

    private final MessageService messageService;
    private final MessageRestMapper messageMapper;

    @Override
    public ResponseEntity<Void> create( final CreateMessageInput input ) {
        this.messageService.create( this.messageMapper.toCommand( input ));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<MessageInfoOutput> findById(final Long id ) {
        final MessageInfo result = this.messageService.get( id );
        return ResponseEntity.ok( this.messageMapper.toOutput( result ));
    }

    @Override
    public ResponseEntity<List<MessageInfoOutput>> findAll() {
        final List<MessageInfo> result = this.messageService.all();
        return result.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok( result.stream().map( this.messageMapper::toOutput ).toList());
    }
}
