package com.codenaiten.template.server.infra.adapter.provider;

import com.codenaiten.template.server.core.shared.spi.AuthProviderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthProviderAdapter implements AuthProviderPort{

    @Override
    public Optional<UUID> getCurrentUserId() {
        return Optional.of( UUID.fromString( "84a5122c-1646-4ff4-83d0-cb618418c34f" ));
    }
}
