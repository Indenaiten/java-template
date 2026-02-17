package com.codenaiten.template.server.web.rest.shared.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RestResponseCode{

    SUCCESS( 0, "Ok"),
    ERROR( 1, "Unexpected error" );

    private final int code;
    private final String defaultMessage;

}
