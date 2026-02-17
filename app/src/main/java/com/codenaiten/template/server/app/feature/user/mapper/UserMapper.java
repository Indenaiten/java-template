package com.codenaiten.template.server.app.feature.user.mapper;

import com.codenaiten.template.server.app.shared.mapper.OptionalMapper;
import com.codenaiten.template.server.core.feature.user.User;
import com.codenaiten.template.server.core.feature.user.dto.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.dto.UserPublicInfo;
import com.codenaiten.template.server.core.shared.dto.Page;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring",
         uses = { OptionalMapper.class },
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface UserMapper{

    UserPrivateInfo toPrivateInfo( User src );
    List<UserPrivateInfo> toPrivateInfo( List<User> src );
    Page<UserPrivateInfo> toPrivateInfo( Page<User> src );

    UserPublicInfo toPublicInfo( User src );
    List<UserPublicInfo> toPublicInfo( List<User> src );
    Page<UserPublicInfo> toPublicInfo( Page<User> src );
}
