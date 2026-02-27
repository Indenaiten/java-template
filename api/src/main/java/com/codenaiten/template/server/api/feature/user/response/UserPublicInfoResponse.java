package com.codenaiten.template.server.api.feature.user.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserPublicInfoResponse(
        UUID id,
        String username,
        String name,
        String surname,
        LocalDateTime createdAt
) {
}

