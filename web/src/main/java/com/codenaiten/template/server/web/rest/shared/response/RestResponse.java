package com.codenaiten.template.server.web.rest.shared.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class RestResponse<T>{

    private final int code;
    private final String message;
    private final T data;

    public static Builder code( final RestResponseCode code ){
        return new Builder( code );
    }

    public static Builder success( ){
        return new Builder( RestResponseCode.SUCCESS );
    }

    public static Builder error( ){
        return new Builder( RestResponseCode.ERROR );
    }

    @RequiredArgsConstructor
    public static class Builder{
        private final @NonNull RestResponseCode code;
        private String message;

        public Builder message( final String message ){
            this.message = message;
            return this;
        }

        public RestResponse<Void> build(){
            final String message = Optional.ofNullable( this.message ).orElse( this.code.getDefaultMessage() );
            return new RestResponse<>( this.code.getCode(), message, null );
        }

        public <T> RestResponse<T> build( T data ){
            final String message = Optional.ofNullable( this.message ).orElse( this.code.getDefaultMessage() );
            return new RestResponse<>( this.code.getCode(), message, data );
        }
    }

}
