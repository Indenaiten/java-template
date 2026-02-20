package com.codenaiten.template.server.web.rest.feature.user.api;

import com.codenaiten.template.server.web.rest.feature.user.dto.request.CreateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.UpdateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.UserSearchRequest;
import com.codenaiten.template.server.web.rest.feature.user.dto.response.UserPrivateInfoResponse;
import com.codenaiten.template.server.web.rest.feature.user.dto.response.UserPublicInfoResponse;
import com.codenaiten.template.server.web.rest.shared.dto.response.PageResponse;
import com.codenaiten.template.server.web.rest.shared.dto.response.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping( "/user" )
public interface UserApi {

    @PostMapping( "/register" )
    ResponseEntity<RestResponse<UserPrivateInfoResponse>> register( @RequestBody CreateUserRequest request );

    @GetMapping( "/public/{userId}" )
    ResponseEntity<RestResponse<UserPublicInfoResponse>> getPublicInfo( @PathVariable UUID userId );

    @GetMapping( "/me" )
    ResponseEntity<RestResponse<UserPrivateInfoResponse>> getMyInfo();

    @PutMapping( "/{userId}" )
    ResponseEntity<RestResponse<UserPrivateInfoResponse>> update( @PathVariable UUID userId, @RequestBody UpdateUserRequest request );

    @PostMapping( "/search" )
    ResponseEntity<RestResponse<PageResponse<UserPublicInfoResponse>>> search(
            @RequestBody( required = false ) UserSearchRequest request
    );
}
