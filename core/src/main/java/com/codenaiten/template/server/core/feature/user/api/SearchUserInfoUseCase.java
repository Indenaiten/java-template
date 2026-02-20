package com.codenaiten.template.server.core.feature.user.api;

import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;

public interface SearchUserInfoUseCase{

    PageInfo<UserPublicInfo> run( String search, PageQuery pageQuery );
}
