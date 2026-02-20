package com.codenaiten.template.server.core.feature.user.dto.command;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Data
@RequiredArgsConstructor
public class RegisterUserCommand{
    private @NonNull String email;
    private @NonNull String username;
    private @NonNull String name;
    private String surname;
    private @NonNull LocalDate birthdate;
    private @NonNull String password;

    public Optional<String> getSurname(){
        return Optional.ofNullable( this.surname );
    }
}
