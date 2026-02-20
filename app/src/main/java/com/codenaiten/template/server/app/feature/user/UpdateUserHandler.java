package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.feature.user.mapper.UserMapper;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.api.UpdateUserUseCase;
import com.codenaiten.template.server.core.feature.user.dto.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.exception.UserNotFoundException;
import com.codenaiten.template.server.core.feature.user.policy.UserAccessPolicy;
import com.codenaiten.template.server.core.feature.user.UserAuthService;
import com.codenaiten.template.server.core.feature.user.UserService;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateUserHandler implements UpdateUserUseCase {

    private final UserAuthService userAuthService;
    private final UserService userService;
    private final UserRepositoryPort userRepository;
    private final UserAccessPolicy userAccessPolicy;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserPrivateInfo run( final UUID userId, final UpdateUserCommand command ){
        // Step 01: Get authenticated user
        final User auth = this.userAuthService.getCurrentUserOrThrow();

        // Step 02: Get user
        final User user = this.userRepository.findById( userId ).orElseThrow( () -> new UserNotFoundException( userId ));

        // Step 03: Check access
        this.userAccessPolicy.authorizeWrite( auth, user );

        // Step 04: Update user
        final User update = this.userService.update( user, command );

        // Step 05: Save changes if there are
        if( update.different( user )) this.userRepository.save( update );

        // Step 06: Return user private info
        return this.userMapper.toPrivateInfo( update );
    }
}
