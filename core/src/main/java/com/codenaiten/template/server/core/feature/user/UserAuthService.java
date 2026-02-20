package com.codenaiten.template.server.core.feature.user;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.exception.UserAuthenticationNotFoundException;
import com.codenaiten.template.server.core.feature.user.exception.UserNotFoundException;
import com.codenaiten.template.server.core.feature.user.spi.UserAuthProviderPort;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserAuthService{

    private final UserRepositoryPort userRepositoryPort;
    private final UserAuthProviderPort userAuthProvider;

    public Optional<User> getCurrentUser(){
        Optional<User> result = Optional.empty();
        try{
            final User user = this.getCurrentUserOrThrow();
            result = Optional.of( user );
        }
        catch( final UserAuthenticationNotFoundException | UserNotFoundException e ){
            log.debug( e.getMessage(), e );
        }
        return result;
    }

    public User getCurrentUserOrThrow(){
        final UUID currentUserId = this.userAuthProvider.getCurrentUserId()
                .orElseThrow( UserAuthenticationNotFoundException::new );
        return this.userRepositoryPort.findById( currentUserId )
                .orElseThrow( () -> new UserNotFoundException( currentUserId ));
    }
}
