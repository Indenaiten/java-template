package com.codenaiten.template.server.core.feature.user.dto.command;

import com.codenaiten.template.server.core.shared.dto.Empty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

public record UpdateUserCommand(
        Empty<String> email,
        Empty<String> username,
        Empty<String> name,
        Empty<String> surname,
        Empty<LocalDate> birthdate,
        Empty<String> password
){
}
