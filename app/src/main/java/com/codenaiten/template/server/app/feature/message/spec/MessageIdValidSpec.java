package com.codenaiten.template.server.app.feature.message.spec;

import com.codenaiten.template.server.app.feature.message.Message;
import com.codenaiten.template.server.app.feature.message.spi.MessageRepository;
import com.codenaiten.template.server.app.shared.spec.Specification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class MessageIdValidSpec implements Specification<Message> {

    private final MessageRepository messageRepository;

    @Override
    public boolean test( final Message candidate ){
        final Long id = Optional.ofNullable( candidate ).map( Message::getId ).orElse( null );
        return Objects.nonNull( id ) && id > 0 && !this.messageRepository.exists( id );
    }
}
