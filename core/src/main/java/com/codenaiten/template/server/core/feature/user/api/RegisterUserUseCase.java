package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;

public interface RegisterUserUseCase {

    UserPrivateInfo run( RegisterUserCommand command );
}
