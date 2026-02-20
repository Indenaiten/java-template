package com.codenaiten.template.server.infra.adapter.feature.user;

import com.codenaiten.template.server.core.feature.user.spi.UserAuthProviderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAuthProviderAdapter implements UserAuthProviderPort {

    @Override
    public Optional<UUID> getCurrentUserId() {
        return Optional.of( UUID.fromString( "84a5122c-1646-4ff4-83d0-cb618418c34f" ));
    }
}
