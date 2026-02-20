package com.codenaiten.template.server.web.rest.shared.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class RestResponse<T>{

    private final int code;
    private final String message;
    private final T data;

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
            final String message = Optional.ofNullable( this.message ).orElse( this.code.defaultMessage() );
            return new RestResponse<>( this.code.code(), message, null );
        }

        public <T> RestResponse<T> build( T data ){
            final String message = Optional.ofNullable( this.message ).orElse( this.code.defaultMessage() );
            return new RestResponse<>( this.code.code(), message, data );
        }
    }

}
