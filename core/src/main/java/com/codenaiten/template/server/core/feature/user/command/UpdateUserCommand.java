package com.codenaiten.template.server.core.feature.user.command;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Data
@RequiredArgsConstructor
public class UpdateUserCommand {
    private String email;
    private String username;
    private String name;
    private Optional<String> surname;
    private LocalDate birthdate;
    private String password;
}
