
package com.codenaiten.template.server.core.feature.user.dto.result;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserPublicInfo(
        UUID id,
        String username,
        String name,
        String surname,
        LocalDateTime createdAt
){
}
