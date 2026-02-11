package com.codenaiten.template.server.app.feature.message.util;

import com.codenaiten.template.server.app.feature.message.Message;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
public class MessageFactory {

    public Message create( final UUID owner, final String message ){
        final LocalDateTime timestamp = LocalDateTime.now();
        return Message.builder().owner( owner ).text( message ).createdAt( timestamp ).updatedAt( timestamp ).build();
    }
}
