package com.codenaiten.template.server.core.feature.user.dto;

import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserFilterQuery extends FilterQuery<UserFilterQuery.Test>{
    @Slf4j
    @Getter
    @RequiredArgsConstructor
    public enum Test implements FilterQuery.Field{
        ID( "id", UUID.class );

        private final String name;
        private final Class<?> type;
    }

    public UserFilterQuery( final List<Group<Test>> groups ){
        super( groups );
        UserFilterQuery.greaterThan( UserField.BIRTHDATE, LocalDate.now() );
    }
}
