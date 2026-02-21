package com.codenaiten.template.server.core.feature.user.spec;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.spi.UserMinimumAgeProviderPort;
import com.codenaiten.template.server.core.shared.spec.Specification;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UserMinimumAgeSpec implements Specification<User>{

    public final @NonNull UserMinimumAgeProviderPort userMinimumAgeProvider;

    @Override
    public boolean test( final User candidate ){
        if( Objects.isNull( candidate )) throw new IllegalArgumentException( "User candidate is required" );
        return candidate.getBirthdate().getYear() >= this.userMinimumAgeProvider.getMinimumAge();
    }
}
