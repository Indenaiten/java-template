package com.codenaiten.template.server.infra.web.rest.api.feature.user.response;

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

