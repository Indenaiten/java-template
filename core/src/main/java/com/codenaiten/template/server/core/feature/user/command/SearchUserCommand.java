package com.codenaiten.template.server.core.feature.user.command;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Data
@Builder
public class SearchUserCommand{
    private String username;
    private String name;
    private Optional<String> surname;
}
