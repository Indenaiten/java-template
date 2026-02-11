package com.codenaiten.template.server.app.feature.message.spi;

import com.codenaiten.template.server.app.feature.message.Message;

import java.util.List;
import java.util.Optional;

public interface MessageRepository {

    void save( Message message );
    Long count();
    boolean exists(Long id );
    Optional<Message> findById( Long id );
    List<Message> findAll();
}
