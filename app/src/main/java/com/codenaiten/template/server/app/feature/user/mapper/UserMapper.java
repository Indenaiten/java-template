package com.codenaiten.template.server.app.feature.user.mapper;

import com.codenaiten.template.server.app.shared.mapper.AppMapper;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring",
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface UserMapper extends AppMapper{

    UserPrivateInfo toPrivateInfo( User src );
    List<UserPrivateInfo> toPrivateInfo( List<User> src );
    PageInfo<UserPrivateInfo> toPrivateInfo( PageInfo<User> src );

    UserPublicInfo toPublicInfo( User src );
    List<UserPublicInfo> toPublicInfo( List<User> src );
    PageInfo<UserPublicInfo> toPublicInfo( PageInfo<User> src );
}
