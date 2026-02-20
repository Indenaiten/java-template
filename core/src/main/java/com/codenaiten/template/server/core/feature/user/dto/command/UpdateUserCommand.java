package com.codenaiten.template.server.core.feature.user.dto.command;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Setter
@Getter
@Accessors( fluent = true )
@RequiredArgsConstructor
public class UpdateUserCommand{
    private final @NonNull String email;
    private final @NonNull String username;
    private final @NonNull String name;
    private String surname;
    private final @NonNull LocalDate birthdate;

    public Optional<String> surname(){
        return Optional.ofNullable( this.surname );
    }
}
