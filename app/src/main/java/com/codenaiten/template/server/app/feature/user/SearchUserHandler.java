package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.feature.user.mapper.UserMapper;
import com.codenaiten.template.server.core.feature.user.User;
import com.codenaiten.template.server.core.feature.user.api.SearchUserInfoUseCase;
import com.codenaiten.template.server.core.feature.user.dto.UserPublicInfo;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.command.PageCommand;
import com.codenaiten.template.server.core.shared.dto.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchUserHandler implements SearchUserInfoUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final UserMapper userMapper;

    @Override
    public Page<UserPublicInfo> run( final String search, final PageCommand pageCommand ) {
        // Step 01: Search Users
        final Page<User> page = this.userRepositoryPort.search( search, pageCommand.getPage(), pageCommand.getSize() );

        // Step 02: Return User Public Info PAge
        return this.userMapper.toPublicInfo( page );
    }
}
