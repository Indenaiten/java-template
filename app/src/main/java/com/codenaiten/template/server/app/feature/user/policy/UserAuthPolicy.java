package com.codenaiten.template.server.app.feature.user.policy;

import com.codenaiten.template.server.app.feature.user.User;
import com.codenaiten.template.server.app.feature.user.exception.UserNotAuthenticatedException;
import com.codenaiten.template.server.app.feature.user.exception.UserNotFoundException;
import com.codenaiten.template.server.app.feature.user.spi.UserAuthProvider;
import com.codenaiten.template.server.app.feature.user.spi.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UserAuthPolicy {

    private final UserAuthProvider userAuthProvider;
    private final UserRepository userRepository;

    public User getCurrentUser() throws UserNotAuthenticatedException, UserNotFoundException {
        final UUID userId = this.userAuthProvider.getCurrentUserId().orElseThrow( UserNotAuthenticatedException::new );
        return this.userRepository.findById( userId ).orElseThrow( () -> new UserNotFoundException( userId ));
    }
}
