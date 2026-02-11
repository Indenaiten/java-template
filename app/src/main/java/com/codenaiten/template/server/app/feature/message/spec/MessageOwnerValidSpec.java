package com.codenaiten.template.server.app.feature.message.spec;

import com.codenaiten.template.server.app.feature.message.Message;
import com.codenaiten.template.server.app.feature.user.spi.UserRepository;
import com.codenaiten.template.server.app.shared.spec.Specification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class MessageOwnerValidSpec implements Specification<Message> {

    private final UserRepository userRepository;

    @Override
    public boolean test( final Message candidate ){
        final UUID owner = Optional.ofNullable( candidate ).map( Message::getOwner ).orElse( null );
        return Objects.nonNull( owner ) && this.userRepository.exists( owner );
    }
}
