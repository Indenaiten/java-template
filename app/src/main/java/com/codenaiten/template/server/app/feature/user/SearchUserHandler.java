package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.feature.user.mapper.UserMapper;
import com.codenaiten.template.server.core.feature.user.dto.UserField;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.api.SearchUserInfoUseCase;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
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
    public PageInfo<UserPublicInfo> run( final FilterQuery<UserField> filterQuery, final PageQuery pageQuery ) {
        // Step 01: Search Users
        final PageInfo<User> page = this.userRepositoryPort.search( filterQuery, pageQuery );

        // Step 02: Return User Public Info PAge
        return this.userMapper.toPublicInfo( page );
    }
}
