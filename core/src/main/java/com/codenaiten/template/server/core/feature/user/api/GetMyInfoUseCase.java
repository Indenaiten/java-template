package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.UserPrivateInfo;

import java.util.UUID;

public interface GetMyInfoUseCase{

    UserPrivateInfo run();
}
