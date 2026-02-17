package com.codenaiten.template.server.web.rest.feature.user.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserPrivateInfoResponse( UUID id,
                                       String email,
                                       String username,
                                       String name,
                                       String surname,
                                       LocalDate birthdate,
                                       LocalDateTime createdAt,
                                       LocalDateTime updatedAt
) {
}
