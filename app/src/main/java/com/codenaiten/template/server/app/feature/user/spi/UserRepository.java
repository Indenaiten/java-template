package com.codenaiten.template.server.app.feature.user.spi;

import com.codenaiten.template.server.app.feature.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    boolean exists( UUID id );
    Optional<User> findById( UUID id );
}
