package com.codenaiten.template.server.web.rest.feature.user.api.request;

import java.time.LocalDate;

public record CreateUserRequest(
        String email,
        String username,
        String name,
        String surname,
        LocalDate birthdate,
        String password
) {
}
