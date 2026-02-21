package com.codenaiten.template.server.core.shared.properties;

import java.util.*;

/**
 * Clase base para las clases que representan propiedades de la aplicación.
 */
public abstract class Properties {

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| HELPER METHODS |--------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Convierte una cadena de texto en una lista de cadenas de texto.
     * Ejemplos:
     * - "a,b,c,d" -> ["a", "b", "c", "d"]
     * - "a, b, c, d" -> ["a", "b", "c", "d"]
     * - "a, 'b c', d" -> ["a", "b c", "d"]
     *
     * @param value Cadena de texto a convertir.
     *
     * @return Lista de cadenas de texto.
     */
    protected List<String> convertToList( final String value ) {
        if( Objects.isNull( value ) || value.isBlank() ) return Collections.emptyList();
        return Arrays.stream( value.split( "," ) )
                .map( item -> item.trim().replace("'", "") )
                .toList();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Obtiene un {@link Optional} a partir de un valor.
     *
     * @param <T> Tipo del valor.
     * @param value Valor a convertir en {@link Optional}.
     *
     * @return Valor {@link Optional}.
     */
    protected <T> Optional<T> getOptional( final T value ) {
        if( value instanceof String valueString && valueString.isBlank() ) return Optional.empty();
        return Optional.ofNullable( value );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}

