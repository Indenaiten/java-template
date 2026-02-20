package com.codenaiten.template.server.infra.web.rest.api.feature.user.request;

import java.time.LocalDate;

public record UpdateUserRequest(
        String email,
        String username,
        String name,
        String surname,
        LocalDate birthdate
) {
}

