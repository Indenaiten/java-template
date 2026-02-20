package com.codenaiten.template.server.core.feature.user.dto;

import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum UserField implements FilterQuery.Field{
    ID( "id", UUID.class ),
    EMAIL( "email", String.class ),
    USERNAME( "username", String.class ),
    NAME( "name", String.class ),
    SURNAME( "surname", String.class ),
    BIRTHDATE( "birthdate", LocalDate.class ),
    PASSWORD( "password", String.class ),
    CREATED_AT( "createdAt", LocalDateTime.class ),
    UPDATED_AT( "updatedAt", LocalDateTime.class );

    private final String name;
    private final Class<?> type;

}
