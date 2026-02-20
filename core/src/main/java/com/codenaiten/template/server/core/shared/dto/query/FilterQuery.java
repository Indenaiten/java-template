package com.codenaiten.template.server.core.shared.dto.query;

import lombok.Getter;

import java.time.temporal.Temporal;
import java.util.*;

public record FilterQuery( List<Group> groups ){

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| STATIC FACTORY METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Crea un filtro de igualdad.
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link Object} El valor a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition equals( final Field field, final Object value ) {
        validateValueType( field, value );
        return new Condition( field, Operator.EQUALS, value );
    }

    /**
     * Crea un filtro de desigualdad.
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link Object} El valor a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition notEquals( final Field field, final Object value ) {
        validateValueType( field, value );
        return new Condition( field, Operator.NOT_EQUALS, value );
    }

    /**
     * Crea un filtro de mayor que.
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link Object} El valor a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition greaterThan( final Field field, final Object value ) {
        validateComparable( field );
        validateValueType( field, value );
        return new Condition( field, Operator.GREATER_THAN, value );
    }

    /**
     * Crea un filtro de mayor o igual que.
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link Object} El valor a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition greaterThanOrEquals( final Field field, final Object value ) {
        validateComparable( field );
        validateValueType( field, value );
        return new Condition( field, Operator.GREATER_THAN_OR_EQUALS, value );
    }

    /**
     * Crea un filtro de menor que.
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link Object} El valor a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition lessThan( final Field field, final Object value ) {
        validateComparable( field );
        validateValueType( field, value );
        return new Condition( field, Operator.LESS_THAN, value );
    }

    /**
     * Crea un filtro de menor o igual que.
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link Object} El valor a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition lessThanOrEquals( final Field field, final Object value ) {
        validateComparable( field );
        validateValueType( field, value );
        return new Condition( field, Operator.LESS_THAN_OR_EQUALS, value );
    }

    /**
     * Crea un filtro de tipo LIKE (contiene).
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link String} El patrón a buscar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition like( final Field field, final String value ) {
        validateString( field );
        return new Condition( field, Operator.LIKE, value );
    }

    /**
     * Crea un filtro de tipo NOT LIKE (no contiene).
     *
     * @param field {@link Field} El campo a filtrar.
     * @param value {@link String} El patrón a buscar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition notLike( final Field field, final String value ) {
        validateString( field );
        return new Condition( field, Operator.NOT_LIKE, value );
    }

    /**
     * Crea un filtro de tipo IN (está en la lista).
     *
     * @param field {@link Field} El campo a filtrar.
     * @param values {@link Collection} Los valores a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition in( final Field field, final Collection<?> values ) {
        validateCollectionTypes( field, values );
        return new Condition( field, Operator.IN, values );
    }

    /**
     * Crea un filtro de tipo NOT IN (no está en la lista).
     *
     * @param field {@link Field} El campo a filtrar.
     * @param values {@link Collection} Los valores a comparar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition notIn( final Field field, final Collection<?> values ) {
        validateCollectionTypes( field, values );
        return new Condition( field, Operator.NOT_IN, values );
    }

    /**
     * Crea un filtro de tipo BETWEEN (entre dos valores).
     *
     * @param field {@link Field} El campo a filtrar.
     * @param start {@link Object} El valor inicial del rango.
     * @param end {@link Object} El valor final del rango.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition between( final Field field, final Object start, final Object end ) {
        validateComparable( field );
        validateValueType( field, start );
        validateValueType( field, end );
        return new Condition( field, Operator.BETWEEN, new Object[]{ start, end } );
    }

    /**
     * Crea un filtro de tipo IS NULL.
     *
     * @param field {@link Field} El campo a filtrar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition isNull( final Field field ) {
        return new Condition( field, Operator.IS_NULL, null );
    }

    /**
     * Crea un filtro de tipo IS NOT NULL.
     *
     * @param field {@link Field} El campo a filtrar.
     *
     * @return {@link Condition} La condición de filtro.
     */
    public static Condition isNotNull( final Field field ) {
        return new Condition( field, Operator.IS_NOT_NULL, null );
    }

    /**
     * Crea un grupo de condiciones unidas por AND.
     *
     * @param conditions {@link List} Lista de condiciones a unir.
     *
     * @return {@link FilterBuilder} Builder para continuar construyendo el filtro.
     */
    public static FilterBuilder and( final List<Condition> conditions ) {
        return new FilterBuilder().and( conditions );
    }

    /**
     * Crea un grupo de condiciones unidas por OR.
     *
     * @param conditions {@link List} Lista de condiciones a unir.
     *
     * @return {@link FilterBuilder} Builder para continuar construyendo el filtro.
     */
    public static FilterBuilder or( final List<Condition> conditions ) {
        return new FilterBuilder().or( conditions );
    }

    /**
     * Crea un filtro a partir de una única condición.
     *
     * @param condition {@link Condition} La condición de filtro.
     *
     * @return {@link FilterBuilder} Builder para continuar construyendo el filtro.
     */
    public static FilterBuilder where( final Condition condition ) {
        return new FilterBuilder().and( List.of( condition ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| GETTERS |---------------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Obtiene los grupos de filtros.
     *
     * @return {@link List} Lista inmutable de grupos de filtros.
     */
    public List<Group> getGroups() {
        return Collections.unmodifiableList( this.groups );
    }

    /**
     * Indica si el filtro está vacío (sin condiciones).
     *
     * @return {@code true} si no hay condiciones, {@code false} en caso contrario.
     */
    public boolean isEmpty() {
        return this.groups.isEmpty();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| INNER CLASSES |---------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Enumeración de operadores de filtro disponibles.
     */
    public enum Operator{
        EQUALS,
        NOT_EQUALS,
        GREATER_THAN,
        GREATER_THAN_OR_EQUALS,
        LESS_THAN,
        LESS_THAN_OR_EQUALS,
        LIKE,
        NOT_LIKE,
        IN,
        NOT_IN,
        BETWEEN,
        IS_NULL,
        IS_NOT_NULL
    }

    /**
     * Tipo de conjunción lógica para los grupos de filtros.
     */
    public enum LogicalOperator {
        AND,
        OR
    }

    public interface Field {
        String getName();
        Class<?> getType();
    }

    /**
     * Representa una condición individual de filtro.
     */
    public static class Condition{

        private final Field field;
        private final Operator operator;
        private final Object value;

        public Condition( final Field field, final Operator operator, final Object value ) {
            this.field = Objects.requireNonNull( field, "El campo no puede ser nulo" );
            this.operator = Objects.requireNonNull( operator, "El operador no puede ser nulo" );
            this.value = value;
        }

        public Field getField() {
            return this.field;
        }

        public Operator getOperator() {
            return this.operator;
        }

        public Object getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return String.format( "%s %s %s", this.field.getName(), this.operator, this.value );
        }
    }

    /**
     * Representa un grupo de condiciones unidas por un operador lógico.
     */
    @Getter
    public static class Group{

        private final LogicalOperator logicalOperator;
        private final List<Condition> conditions;

        public Group( final LogicalOperator logicalOperator, final List<Condition> conditions ) {
            this.logicalOperator = Objects.requireNonNull( logicalOperator, "El operador lógico no puede ser nulo" );
            this.conditions = new ArrayList( Objects.requireNonNull( conditions, "Las condiciones no pueden ser nulas" ) );
        }

        public List<Condition> getConditions() {
            return Collections.unmodifiableList( this.conditions );
        }

        @Override
        public String toString() {
            return String.format( "(%s: %s)", this.logicalOperator, this.conditions );
        }
    }

    /**
     * Builder para construir filtros de forma fluida.
     */
    public static class FilterBuilder {

        private final List<Group> groups;

        private FilterBuilder() {
            this.groups = new ArrayList();
        }

        /**
         * Añade un grupo de condiciones unidas por AND.
         *
         * @param conditions {@link List} Lista de condiciones a unir.
         *
         * @return {@link FilterBuilder} Este builder para encadenamiento.
         */
        public FilterBuilder and( final List<Condition> conditions ) {
            if ( Objects.nonNull( conditions ) && !conditions.isEmpty() ) {
                this.groups.add( new Group( LogicalOperator.AND, conditions ) );
            }
            return this;
        }

        /**
         * Añade un grupo de condiciones unidas por OR.
         *
         * @param conditions {@link List} Lista de condiciones a unir.
         *
         * @return {@link FilterBuilder} Este builder para encadenamiento.
         */
        public FilterBuilder or( final List<Condition> conditions ) {
            if ( Objects.nonNull( conditions ) && !conditions.isEmpty() ) {
                this.groups.add( new Group( LogicalOperator.OR, conditions ) );
            }
            return this;
        }

        /**
         * Construye el filtro con todas las condiciones añadidas.
         *
         * @return {@link FilterQuery} El filtro construido.
         */
        public FilterQuery build() {
            return new FilterQuery( this.groups );
        }
    }

// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String toString() {
        return "Filter{" + "groups=" + this.groups + '}';
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| VALIDATION METHODS |----------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    /**
     * Valida que el valor sea compatible con el tipo del campo.
     *
     * @param field {@link Field} El campo a validar.
     * @param value {@link Object} El valor a validar.
     *
     * @throws IllegalArgumentException Si el valor no es compatible con el tipo del campo.
     */
    private static void validateValueType( final Field field, final Object value ) {
        if ( Objects.isNull( value ) ) {
            return;
        }
        final Class<?> fieldType = field.getType();
        if ( !fieldType.isAssignableFrom( value.getClass() ) ) {
            throw new IllegalArgumentException( String.format(
                "El valor '%s' de tipo '%s' no es compatible con el campo '%s' de tipo '%s'",
                value, value.getClass().getSimpleName(), field.getName(), fieldType.getSimpleName()
            ));
        }
    }

    /**
     * Valida que el campo sea de tipo comparable (Number, Date, Temporal).
     *
     * @param field {@link Field} El campo a validar.
     *
     * @throws IllegalArgumentException Si el campo no es comparable.
     */
    private static void validateComparable( final Field field ) {
        final Class<?> fieldType = field.getType();
        final boolean isComparable = Comparable.class.isAssignableFrom( fieldType )
                || Number.class.isAssignableFrom( fieldType )
                || Date.class.isAssignableFrom( fieldType )
                || Temporal.class.isAssignableFrom( fieldType )
                || fieldType.isPrimitive();

        if ( !isComparable ) {
            throw new IllegalArgumentException( String.format(
                "El campo '%s' de tipo '%s' no es comparable. " +
                "Las operaciones de comparación solo están disponibles para tipos numéricos, fechas y comparables.",
                field.getName(), fieldType.getSimpleName()
            ));
        }
    }

    /**
     * Valida que el campo sea de tipo String.
     *
     * @param field {@link Field} El campo a validar.
     *
     * @throws IllegalArgumentException Si el campo no es de tipo String.
     */
    private static void validateString( final Field field ) {
        final Class<?> fieldType = field.getType();
        if ( !String.class.isAssignableFrom( fieldType ) ) {
            throw new IllegalArgumentException( String.format(
                "El campo '%s' de tipo '%s' no es de tipo String. " +
                "Las operaciones LIKE solo están disponibles para campos de tipo String.",
                field.getName(), fieldType.getSimpleName()
            ));
        }
    }

    /**
     * Valida que todos los valores de la colección sean compatibles con el tipo del campo.
     *
     * @param field {@link Field} El campo a validar.
     * @param values {@link Collection} Los valores a validar.
     *
     * @throws IllegalArgumentException Si algún valor no es compatible con el tipo del campo.
     */
    private static void validateCollectionTypes( final Field field, final Collection<?> values ) {
        if ( Objects.isNull( values ) || values.isEmpty() ) {
            return;
        }
        for ( final Object value : values ) {
            validateValueType( field, value );
        }
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
