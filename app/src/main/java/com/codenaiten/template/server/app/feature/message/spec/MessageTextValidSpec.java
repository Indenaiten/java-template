package com.codenaiten.template.server.app.feature.message.spec;

import com.codenaiten.template.server.app.feature.message.Message;
import com.codenaiten.template.server.app.shared.spec.Specification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class MessageTextValidSpec implements Specification<Message> {

    @Override
    public boolean test( final Message candidate ) {
        final String text = Optional.ofNullable( candidate ).map( Message::getText ).orElse( null );
        return Objects.nonNull( text ) && !text.isBlank();
    }
}
