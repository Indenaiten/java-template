
package com.codenaiten.template.server.core.feature.user.dto.result;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserPrivateInfo( UUID id,
                               String email,
                               String username,
                               String name,
                               String surname,
                               LocalDate birthdate,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt
){
}
