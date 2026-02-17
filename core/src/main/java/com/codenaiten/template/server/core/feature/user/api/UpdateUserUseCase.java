package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.UserPrivateInfo;

import java.util.UUID;

public interface UpdateUserUseCase {

    UserPrivateInfo run( UUID userId, UpdateUserCommand command );
}
