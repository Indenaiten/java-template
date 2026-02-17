package com.codenaiten.template.server.web.rest.feature.user.api;

import com.codenaiten.template.server.web.rest.feature.user.request.CreateUserRequest;
import com.codenaiten.template.server.web.rest.feature.user.request.UpdateUserRequest;
import com.codenaiten.template.server.web.rest.shared.response.PageResponse;
import com.codenaiten.template.server.web.rest.feature.user.response.UserPrivateInfoResponse;
import com.codenaiten.template.server.web.rest.feature.user.response.UserPublicInfoResponse;
import com.codenaiten.template.server.web.rest.shared.response.RestResponse;
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

    @GetMapping({ "/search/{search}", "/search/{search}/{page}", "/search/{search}/{page}/{size}" })
    ResponseEntity<RestResponse<PageResponse<UserPublicInfoResponse>>> search(
            @PathVariable String search,
            @RequestParam( required = false) Integer page,
            @RequestParam( required = false ) Integer size
    );
}
