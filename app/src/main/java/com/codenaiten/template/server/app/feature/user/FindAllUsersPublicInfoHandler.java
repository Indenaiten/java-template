package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.feature.user.mapper.UserMapper;
import com.codenaiten.template.server.core.feature.user.api.FindAllUsersPublicInfoUseCase;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllUsersPublicInfoHandler implements FindAllUsersPublicInfoUseCase {

    private final UserRepositoryPort userRepository;
    private final UserMapper userMapper;

    @Override
    public PageInfo<UserPublicInfo> run( final PageQuery query ){
        // Step 01: Get all users
        final PageInfo<User> result = this.userRepository.findAll( query );

        // Step 02: Return Page with user public info
        return this.userMapper.toPublicInfo( result );
    }
}
