package com.codenaiten.template.server.core.shared.spi;

import java.util.Optional;
import java.util.UUID;

public interface AuthProviderPort{

    Optional<UUID> getCurrentUserId();
}
