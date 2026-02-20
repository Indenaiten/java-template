package com.codenaiten.template.server.web.rest.feature.user.dto.response;

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

