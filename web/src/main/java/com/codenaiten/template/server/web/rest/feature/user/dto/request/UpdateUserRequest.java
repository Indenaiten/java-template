package com.codenaiten.template.server.web.rest.feature.user.dto.request;

import java.time.LocalDate;

public record UpdateUserRequest(
        String email,
        String username,
        String name,
        String surname,
        LocalDate birthdate
) {
}

