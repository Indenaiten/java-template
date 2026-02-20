package com.codenaiten.template.server.core.shared.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class Empty<T>{

    private final T value;
    private @Getter boolean present = true;

    private Empty( final T value, final boolean present ){
        this.value = value;
        this.present = present;
    }

    public static <T> Empty<T> of( final T value ) {
        return new Empty<>( value );
    }

    public static <T> Empty<T> empty() {
        return new Empty<>(  null, false );
    }

    public Optional<T> get() {
        return Optional.ofNullable( this.value );
    }

    public boolean isEmpty() {
        return !this.present;
    }

    public void ifPresent( final Consumer<? super T> consumer ) {
        if ( this.present ) {
            consumer.accept( this.value );
        }
    }
}
