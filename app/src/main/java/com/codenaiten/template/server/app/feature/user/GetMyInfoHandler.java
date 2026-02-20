package com.codenaiten.template.server.app.feature.user;

import com.codenaiten.template.server.app.feature.user.mapper.UserMapper;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.api.GetMyInfoUseCase;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.policy.UserAccessPolicy;
import com.codenaiten.template.server.core.feature.user.UserAuthService;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetMyInfoHandler implements GetMyInfoUseCase{

    private final UserAuthService userAuthService;
    private final UserRepositoryPort userRepositoryPort;
    private final UserAccessPolicy userAccessPolicy;
    private final UserMapper userMapper;

    @Override
    public UserPrivateInfo run() {
        // Step 01: Get authenticated user
        final User auth = this.userAuthService.getCurrentUserOrThrow();

        // Step 02: Check access
        this.userAccessPolicy.authorizeReadPrivateInfo( auth, auth );

        // Step 03: Return user private info
        return this.userMapper.toPrivateInfo( auth );
    }
}
