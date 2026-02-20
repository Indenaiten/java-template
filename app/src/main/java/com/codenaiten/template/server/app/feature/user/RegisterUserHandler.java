package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.feature.user.mapper.UserMapper;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.api.RegisterUserUseCase;
import com.codenaiten.template.server.core.feature.user.dto.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.UserService;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterUserHandler implements RegisterUserUseCase {

    private final UserService userService;
    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserPrivateInfo run( final RegisterUserCommand command ) {
        // Step 01: Create input from command
        final User user = this.userService.create( command );

        // Step 02: Save user
        this.userRepositoryPort.save( user );

        // Step 03: Return user private info
        return this.userMapper.toPrivateInfo( user );
    }
}
