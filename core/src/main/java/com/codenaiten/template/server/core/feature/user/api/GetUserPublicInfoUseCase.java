package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;

import java.util.UUID;

public interface GetUserPublicInfoUseCase{

    UserPublicInfo run( UUID userId );
}
