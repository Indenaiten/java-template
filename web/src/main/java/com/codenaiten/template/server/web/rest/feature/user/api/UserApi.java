package com.codenaiten.template.server.web.rest.feature.user.api;

import com.codenaiten.template.server.web.rest.feature.user.api.request.CreateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.api.request.UpdateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.api.response.UserPrivateInfoResponse;
import com.codenaiten.template.server.web.rest.feature.user.api.response.UserPublicInfoResponse;
import com.codenaiten.template.server.web.rest.shared.api.response.PageResponse;
import com.codenaiten.template.server.web.rest.shared.api.response.RestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping( "/user" )
public interface UserApi {

    @PostMapping( "/register" )
    ResponseEntity<RestResponse<UserPrivateInfoResponse>> register(
            @RequestBody CreateUserRequest request
    );

    @PutMapping( "/{userId}" )
    ResponseEntity<RestResponse<UserPrivateInfoResponse>> update(
            @PathVariable UUID userId,
            @RequestBody UpdateUserRequest request
    );

    @GetMapping({ "/public/all", "/public/all/{page}", "/public/all/{page}/{size}" })
    ResponseEntity<RestResponse<PageResponse<UserPublicInfoResponse>>> getAll(
            @PathVariable( required = false ) Integer page,
            @PathVariable( required = false ) Integer size
    );

    @GetMapping( "/public/{userId}" )
    ResponseEntity<RestResponse<UserPublicInfoResponse>> getPublicInfo(
            @PathVariable UUID userId
    );

    @GetMapping( "/me" )
    ResponseEntity<RestResponse<UserPrivateInfoResponse>> getMyInfo();
}
