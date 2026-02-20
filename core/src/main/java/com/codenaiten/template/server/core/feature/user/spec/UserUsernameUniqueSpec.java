package com.codenaiten.template.server.core.feature.user.spec;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.spec.Specification;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UserUsernameUniqueSpec implements Specification<User>{

    private final @NonNull UserRepositoryPort userRepositoryPort;

    @Override
    public boolean test( final User candidate ){
        if( Objects.isNull( candidate )) throw new IllegalArgumentException( "User candidate is required" );
        return !this.userRepositoryPort.existsByUsername( candidate.getUsername() );
    }
}
