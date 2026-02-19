package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.UserPublicInfo;
import com.codenaiten.template.server.core.shared.command.PageCommand;
import com.codenaiten.template.server.core.shared.dto.PageInfo;

public interface SearchUserInfoUseCase{

    PageInfo<UserPublicInfo> run(String search, PageCommand pageCommand );
}
