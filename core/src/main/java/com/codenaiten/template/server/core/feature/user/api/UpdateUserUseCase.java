package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;

import java.util.UUID;

public interface UpdateUserUseCase {

    UserPrivateInfo run( UUID userId, UpdateUserCommand command );
}
