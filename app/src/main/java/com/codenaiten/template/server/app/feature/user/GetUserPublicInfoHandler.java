package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.feature.user.mapper.UserMapper;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.api.GetUserPublicInfoUseCase;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.feature.user.exception.UserNotFoundException;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetUserPublicInfoHandler implements GetUserPublicInfoUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;

    @Override
    public UserPublicInfo run( final UUID userId ) {
        // Step 01: Get user
        final User user = this.userRepositoryPort.findById( userId ).orElseThrow( () -> new UserNotFoundException( userId ));

        // Step 02: Return user public info
        return this.userMapper.toPublicInfo( user );
    }
}
