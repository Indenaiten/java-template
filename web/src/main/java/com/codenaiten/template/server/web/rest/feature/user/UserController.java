package com.codenaiten.template.server.web.rest.feature.user;

import com.codenaiten.template.server.core.feature.user.api.*;
import com.codenaiten.template.server.core.feature.user.dto.command.RegisterUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.command.UpdateUserCommand;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPrivateInfo;
import com.codenaiten.template.server.core.feature.user.dto.result.UserPublicInfo;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import com.codenaiten.template.server.web.rest.feature.user.api.UserApi;
import com.codenaiten.template.server.web.rest.feature.user.converter.UserSearchRequestConverter;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.CreateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.UpdateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.UserSearchRequest;
import com.codenaiten.template.server.web.rest.feature.user.dto.response.UserPrivateInfoResponse;
import com.codenaiten.template.server.web.rest.feature.user.dto.response.UserPublicInfoResponse;
import com.codenaiten.template.server.web.rest.shared.dto.response.PageResponse;
import com.codenaiten.template.server.web.rest.shared.dto.response.RestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserRestMapper userMapper;
    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserPublicInfoUseCase getUserPublicInfoUseCase;
    private final GetMyInfoUseCase getMyInfoUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final SearchUserInfoUseCase searchUserInfoUseCase;

    @Override
    public ResponseEntity<RestResponse<UserPrivateInfoResponse>> register( final CreateUserRequest request ) {
        final RegisterUserCommand command = this.userMapper.toCommand( request );
        final UserPrivateInfo result = this.registerUserUseCase.run( command );
        final UserPrivateInfoResponse data = this.userMapper.toResponse( result );
        final var response = RestResponse.success().build( data );
        return ResponseEntity.ok( response );
    }

    @Override
    public ResponseEntity<RestResponse<UserPublicInfoResponse>> getPublicInfo( final UUID userId ) {
        final UserPublicInfo result = this.getUserPublicInfoUseCase.run( userId );
        final UserPublicInfoResponse data = this.userMapper.toResponse( result );
        final var response = RestResponse.success().build( data );
        return ResponseEntity.ok( response );
    }

    @Override
    public ResponseEntity<RestResponse<UserPrivateInfoResponse>> getMyInfo() {
        final UserPrivateInfo result = this.getMyInfoUseCase.run();
        final UserPrivateInfoResponse data = this.userMapper.toResponse( result );
        final var response = RestResponse.success().build( data );
        return ResponseEntity.ok( response );
    }

    @Override
    public ResponseEntity<RestResponse<UserPrivateInfoResponse>> update( final UUID userId, final UpdateUserRequest request ) {
        final UpdateUserCommand command = this.userMapper.toCommand( request );
        final UserPrivateInfo result = this.updateUserUseCase.run( userId, command );
        final UserPrivateInfoResponse data = this.userMapper.toResponse( result );
        final var response = RestResponse.success().build( data );
        return ResponseEntity.ok( response );
    }

    @Override
    public ResponseEntity<RestResponse<PageResponse<UserPublicInfoResponse>>> search( final UserSearchRequest request ) {
        final FilterQuery<UserField> filterQuery = UserSearchRequestConverter.convert( request );
        final PageQuery pageQuery = this.userSearchRequestConverter.toPageQuery( request );
        final PageInfo<UserPublicInfo> result = this.searchUserInfoUseCase.run( filterQuery, pageQuery );
        final PageResponse<UserPublicInfoResponse> data = this.userMapper.toResponse( result );
        final var response = RestResponse.success().build( data );
        return ResponseEntity.ok( response );
    }
}
