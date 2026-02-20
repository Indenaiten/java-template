package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.query.SearchUserQuery;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;

public interface SearchUserInfoUseCase{

    PageInfo<UserPublicInfo> run( SearchUserQuery query );
}
