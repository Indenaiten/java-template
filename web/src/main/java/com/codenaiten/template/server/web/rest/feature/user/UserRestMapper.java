package com.codenaiten.template.server.web.rest.feature.user;

import com.codenaiten.template.server.core.feature.user.dto.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import com.codenaiten.template.server.web.rest.feature.user.request.CreateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.request.UpdateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.response.UserPrivateInfoResponse;
import com.codenaiten.template.server.web.rest.feature.user.response.UserPublicInfoResponse;
import com.codenaiten.template.server.web.rest.shared.mapper.RestMapper;
import com.codenaiten.template.server.web.rest.shared.response.PageResponse;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper( componentModel = "spring",
         uses = { RestMapper.class },
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface UserRestMapper {

    UserPrivateInfoResponse toResponse( UserPrivateInfo src );
    UserPublicInfoResponse toResponse( UserPublicInfo src );

    RegisterUserCommand toCommand( CreateUserRequest src );
    UpdateUserCommand toCommand( UpdateUserRequest src );

    default PageResponse<UserPublicInfoResponse> toPageResponseWithMapping( PageInfo<UserPublicInfo> src ) {
        if( Objects.isNull( src )) return null;

        return new PageResponse<>(
                src.totalElements(),
                src.totalPages(),
                src.pageNumber(),
                src.pageSize(),
                src.content().stream().map( this::toResponse ).toList()
        );
    }
}


