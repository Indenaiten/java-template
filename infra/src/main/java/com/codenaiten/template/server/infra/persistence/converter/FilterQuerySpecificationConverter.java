package com.codenaiten.template.server.infra.persistence.converter;

import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Convertidor genérico que transforma un {@link FilterQuery} del dominio
 * en una {@link Specification} de Spring Data JPA.
 */
public final class FilterQuerySpecificationConverter {

    private FilterQuerySpecificationConverter() {
        // Utility class
    }

    /**
     * Convierte un {@link FilterQuery} en una {@link Specification} de JPA.
     *
     * @param filterQuery {@link FilterQuery} El filtro del dominio a convertir.
     * @param <E> El tipo de la entidad JPA.
     *
     * @return {@link Specification} La especificación JPA equivalente.
     */
    public static <E> Specification<E> convert( final FilterQuery filterQuery, final Class<E> clazz ) {
        if ( filterQuery == null || filterQuery.isEmpty() ) {
            return ( root, query, cb ) -> cb.conjunction();
        }

        return ( root, query, cb ) -> {
            final List<Predicate> groupPredicates = new ArrayList<>();

            for ( final FilterQuery.Group group : filterQuery.getGroups() ) {
                final List<Predicate> conditionPredicates = new ArrayList<>();

                for ( final FilterQuery.Condition condition : group.getConditions() ) {
                    final Predicate predicate = toPredicate( condition, root, cb );
                    conditionPredicates.add( predicate );
                }

                if ( !conditionPredicates.isEmpty() ) {
                    final Predicate[] predicatesArray = conditionPredicates.toArray( new Predicate[0] );
                    final Predicate groupPredicate = switch ( group.getLogicalOperator() ) {
                        case AND -> cb.and( predicatesArray );
                        case OR -> cb.or( predicatesArray );
                    };
                    groupPredicates.add( groupPredicate );
                }
            }

            return cb.and( groupPredicates.toArray( new Predicate[0] ) );
        };
    }

// ------------------------------------------------------------------------------------------------------------------ \\

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <E> Predicate toPredicate(
            final FilterQuery.Condition condition,
            final Root<E> root,
            final CriteriaBuilder cb
    ) {
        final String fieldName = condition.getField().getName();
        final Object value = condition.getValue();

        return switch ( condition.getOperator() ) {
            case EQUALS -> cb.equal( root.get( fieldName ), value );

            case NOT_EQUALS -> cb.notEqual( root.get( fieldName ), value );

            case GREATER_THAN -> cb.greaterThan( root.get( fieldName ), (Comparable) value );

            case GREATER_THAN_OR_EQUALS -> cb.greaterThanOrEqualTo( root.get( fieldName ), (Comparable) value );

            case LESS_THAN -> cb.lessThan( root.get( fieldName ), (Comparable) value );

            case LESS_THAN_OR_EQUALS -> cb.lessThanOrEqualTo( root.get( fieldName ), (Comparable) value );

            case LIKE -> cb.like(
                    cb.lower( root.get( fieldName ) ),
                    "%" + ((String) value).toLowerCase() + "%"
            );

            case NOT_LIKE -> cb.notLike(
                    cb.lower( root.get( fieldName ) ),
                    "%" + ((String) value).toLowerCase() + "%"
            );

            case IN -> root.get( fieldName ).in( (Collection<?>) value );

            case NOT_IN -> cb.not( root.get( fieldName ).in( (Collection<?>) value ) );

            case BETWEEN -> {
                final Object[] range = (Object[]) value;
                yield cb.between( root.get( fieldName ), (Comparable) range[0], (Comparable) range[1] );
            }

            case IS_NULL -> cb.isNull( root.get( fieldName ) );

            case IS_NOT_NULL -> cb.isNotNull( root.get( fieldName ) );
        };
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}


