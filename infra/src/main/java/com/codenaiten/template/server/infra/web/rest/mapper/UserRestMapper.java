package com.codenaiten.template.server.infra.web.rest.mapper;

import com.codenaiten.template.server.core.feature.user.dto.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import com.codenaiten.template.server.infra.web.rest.user.dto.request.CreateUserRequest;
import com.codenaiten.template.server.infra.web.rest.user.dto.request.UpdateUserRequest;
import com.codenaiten.template.server.infra.web.rest.user.dto.response.UserPrivateInfoResponse;
import com.codenaiten.template.server.infra.web.rest.user.dto.response.UserPublicInfoResponse;
import com.codenaiten.template.server.infra.web.rest.shared.dto.response.PageResponse;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface UserRestMapper extends RestMapper{

    RegisterUserCommand toCommand( CreateUserRequest src );
    UpdateUserCommand toCommand( UpdateUserRequest src );

    UserPrivateInfoResponse toResponse( UserPrivateInfo src );
    UserPublicInfoResponse toResponse( UserPublicInfo src );

    default PageResponse<UserPublicInfoResponse> toResponse( final PageInfo<UserPublicInfo> src ) {
        return this.toResponse( src, this::toResponse );
    }
}


