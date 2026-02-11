package com.codenaiten.template.server.app.feature.user.spi;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthProvider {

    Optional<UUID> getCurrentUserId();
}
