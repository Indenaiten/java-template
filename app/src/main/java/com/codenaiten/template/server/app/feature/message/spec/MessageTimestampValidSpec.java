package com.codenaiten.template.server.app.feature.message.spec;

import com.codenaiten.template.server.app.feature.message.Message;
import com.codenaiten.template.server.app.shared.spec.Specification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class MessageTimestampValidSpec implements Specification<Message> {

    @Override
    public boolean test( final Message candidate ) {
        final LocalDateTime createdAt = Optional.ofNullable( candidate ).map( Message::getCreatedAt ).orElse( null );
        final LocalDateTime updatedAt = Optional.ofNullable( candidate ).map( Message::getUpdatedAt ).orElse( null );
        return Objects.nonNull( createdAt ) && Objects.nonNull( updatedAt ) &&
               !updatedAt.isAfter( LocalDateTime.now() ) && !createdAt.isAfter( updatedAt );
    }
}
