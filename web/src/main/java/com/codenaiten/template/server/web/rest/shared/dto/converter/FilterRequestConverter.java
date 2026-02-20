package com.codenaiten.template.server.web.rest.shared.dto.converter;

import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery.Condition;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery.LogicalOperator;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery.Operator;
import com.codenaiten.template.server.web.rest.shared.dto.request.FilterRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Convertidor estático que transforma un {@link FilterRequest} de la capa REST
 * en un {@link FilterQuery} del dominio.
 */
public final class FilterRequestConverter {

    private FilterRequestConverter() {
        // Utility class
    }

    /**
     * Convierte un {@link FilterRequest} en un {@link FilterQuery} del dominio.
     *
     * @param filterRequest {@link FilterRequest} El filtro de la petición REST.
     * @param fieldEnum {@link Class} La clase enum del campo (ej: UserField.class).
     * @param <T> El tipo del campo que extiende {@link FilterQuery.Field}.
     *
     * @return {@link FilterQuery} El filtro del dominio equivalente.
     */
    public static <T extends FilterQuery.Field> FilterQuery<T> convert(
            final FilterRequest filterRequest,
            final Class<T> fieldEnum
    ) {
        if ( Objects.isNull( filterRequest ) ) {
            return new FilterQuery<>( Collections.emptyList() );
        }

        final LogicalOperator logicalOperator = resolveLogicalOperator( filterRequest.logical() );
        final List<Condition<T>> conditions = new ArrayList<>();

        if ( Objects.nonNull( filterRequest.conditions() ) ) {
            for ( final FilterRequest.Condition conditionRequest : filterRequest.conditions() ) {
                conditions.add( toCondition( conditionRequest, fieldEnum ) );
            }
        }

        if ( conditions.isEmpty() ) {
            return new FilterQuery<>( Collections.emptyList() );
        }

        final FilterQuery.Group<T> group = new FilterQuery.Group<>( logicalOperator, conditions );
        return new FilterQuery<>( List.of( group ) );
    }

    /**
     * Convierte una condición REST en una condición de dominio.
     *
     * @param request {@link FilterRequest.Condition} La condición de la petición REST.
     * @param fieldEnum {@link Class} La clase enum del campo.
     * @param <T> El tipo del campo que extiende {@link FilterQuery.Field}.
     *
     * @return {@link Condition} La condición del dominio equivalente.
     */
    private static <T extends FilterQuery.Field> Condition<T> toCondition(
            final FilterRequest.Condition request,
            final Class<T> fieldEnum
    ) {
        final T field = resolveField( request.field(), fieldEnum );
        final Operator operator = Operator.valueOf( request.operator().toUpperCase() );
        final Object value = convertValue( field, operator, request );

        return new Condition<>( field, operator, value );
    }

    /**
     * Convierte el valor de la condición según el tipo del campo y el operador.
     *
     * @param field {@link FilterQuery.Field} El campo del filtro.
     * @param operator {@link Operator} El operador de la condición.
     * @param request {@link FilterRequest.Condition} La condición de la petición.
     *
     * @return {@link Object} El valor convertido.
     */
    private static Object convertValue(
            final FilterQuery.Field field,
            final Operator operator,
            final FilterRequest.Condition request
    ) {
        if ( operator == Operator.IS_NULL || operator == Operator.IS_NOT_NULL ) {
            return null;
        }

        if ( operator == Operator.IN || operator == Operator.NOT_IN ) {
            final List<Object> values = request.values();
            if ( Objects.isNull( values ) ) return Collections.emptyList();
            return values.stream().map( v -> castValue( field, v ) ).toList();
        }

        if ( operator == Operator.BETWEEN ) {
            final List<Object> values = request.values();
            if ( Objects.isNull( values ) || values.size() < 2 ) {
                throw new IllegalArgumentException( "El operador BETWEEN requiere exactamente 2 valores en 'values'" );
            }
            return new Object[]{ castValue( field, values.get( 0 ) ), castValue( field, values.get( 1 ) ) };
        }

        return castValue( field, request.value() );
    }

    /**
     * Convierte un valor según el tipo del campo.
     *
     * @param field {@link FilterQuery.Field} El campo del filtro.
     * @param value {@link Object} El valor a convertir.
     *
     * @return {@link Object} El valor convertido al tipo correcto.
     */
    private static Object castValue( final FilterQuery.Field field, final Object value ) {
        if ( Objects.isNull( value ) ) return null;

        final Class<?> type = field.getType();
        final String stringValue = value.toString();

        if ( UUID.class.equals( type ) ) {
            return UUID.fromString( stringValue );
        }
        if ( LocalDate.class.equals( type ) ) {
            return LocalDate.parse( stringValue );
        }
        if ( LocalDateTime.class.equals( type ) ) {
            return LocalDateTime.parse( stringValue );
        }
        if ( String.class.equals( type ) ) {
            return stringValue;
        }

        return value;
    }

    /**
     * Resuelve el campo enum a partir de su nombre en String.
     *
     * @param fieldName {@link String} El nombre del campo.
     * @param fieldEnum {@link Class} La clase enum del campo.
     * @param <T> El tipo del campo que extiende {@link FilterQuery.Field}.
     *
     * @return {@link T} El campo enum correspondiente.
     *
     * @throws IllegalArgumentException Si el campo no existe en el enum.
     */
    private static <T extends FilterQuery.Field> T resolveField( final String fieldName, final Class<T> fieldEnum ) {
        if ( Objects.isNull( fieldName ) ) {
            throw new IllegalArgumentException( "El nombre del campo no puede ser nulo" );
        }

        // Iteramos sobre los valores del enum para encontrar el campo
        for ( T field : fieldEnum.getEnumConstants() ) {
            if ( ((Enum<?>) field).name().equalsIgnoreCase( fieldName ) ) {
                return field;
            }
        }

        throw new IllegalArgumentException(
            String.format( "Campo '%s' no encontrado en el enum %s", fieldName, fieldEnum.getSimpleName() )
        );
    }

    /**
     * Resuelve el operador lógico desde su representación en String.
     *
     * @param value {@link String} El valor del operador lógico ("AND" o "OR").
     *
     * @return {@link LogicalOperator} El operador lógico, por defecto AND si es nulo.
     */
    private static LogicalOperator resolveLogicalOperator( final String value ) {
        if ( Objects.isNull( value ) ) return LogicalOperator.AND;
        return LogicalOperator.valueOf( value.toUpperCase() );
    }
}

