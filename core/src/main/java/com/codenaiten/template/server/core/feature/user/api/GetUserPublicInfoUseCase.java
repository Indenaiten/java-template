package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.UserPublicInfo;

import java.util.UUID;

public interface GetUserPublicInfoUseCase{

    UserPublicInfo run( UUID userId );
}
