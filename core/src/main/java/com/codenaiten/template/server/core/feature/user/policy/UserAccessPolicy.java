package com.codenaiten.template.server.core.feature.user.policy;

import com.codenaiten.template.server.core.feature.user.User;
import com.codenaiten.template.server.core.feature.user.exception.ReadUserPrivateInfoException;
import com.codenaiten.template.server.core.feature.user.exception.WriteUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class UserAccessPolicy {

    public void authorizeReadPrivateInfo( final User requester, final User user ){
        if( Objects.isNull( requester ) || Objects.isNull( user ))
            throw new IllegalArgumentException( "Requester that read private info and target user are required" );
        if( requester.not( user )) throw new ReadUserPrivateInfoException( requester, user );
    }

    public void authorizeWrite( final User requester, final User user ){
        if( Objects.isNull( requester ) || Objects.isNull( user ))
            throw new IllegalArgumentException( "Requester that write info and target user are required" );
        if( requester.not( user )) throw new WriteUserException( requester, user );
    }
}
