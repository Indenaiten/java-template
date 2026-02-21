package com.codenaiten.template.server.web.rest.shared.api.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

public record RestResponse<T>( int code, String message, T data ){

    @Getter
    @Accessors( fluent = true )
    @RequiredArgsConstructor
    public enum Code{

        SUCCESS( 0, "Ok"),
        ERROR( 1, "Unexpected error" );

        private final int code;
        private final String defaultMessage;

    }

    public static Builder code( final Code code ){
        return new Builder( code );
    }

    public static Builder success( ){
        return new Builder( Code.SUCCESS );
    }

    public static Builder error( ){
        return new Builder( Code.ERROR );
    }

    @RequiredArgsConstructor
    public static class Builder{
        private final @NonNull Code code;
        private String message;

        public Builder message( final String message ){
            this.message = message;
            return this;
        }

        public RestResponse<Void> build(){
            final String messageToBuild = Optional.ofNullable( this.message ).orElse( this.code.defaultMessage() );
            return new RestResponse<>( this.code.code(), messageToBuild, null );
        }

        public <T> RestResponse<T> build( final T data ){
            final String messageToBuild = Optional.ofNullable( this.message ).orElse( this.code.defaultMessage() );
            return new RestResponse<>( this.code.code(), messageToBuild, data );
        }
    }

}
