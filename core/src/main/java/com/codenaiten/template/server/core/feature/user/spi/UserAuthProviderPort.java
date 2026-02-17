package com.codenaiten.template.server.core.feature.user.spi;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthProviderPort {

    Optional<UUID> getCurrentUserId();
}
