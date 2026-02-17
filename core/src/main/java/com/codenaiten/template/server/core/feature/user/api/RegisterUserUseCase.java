package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.UserPrivateInfo;

public interface RegisterUserUseCase {

    UserPrivateInfo run( RegisterUserCommand command );
}
