package com.codenaiten.template.server.web.rest.feature.user.converter;

import com.codenaiten.template.server.core.feature.user.dto.UserField;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery.Condition;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery.LogicalOperator;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery.Operator;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.UserSearchRequest;
import com.codenaiten.template.server.web.rest.shared.dto.request.FilterConditionRequest;
import com.codenaiten.template.server.web.rest.shared.dto.request.FilterGroupRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class UserSearchRequestConverter {

    public FilterQuery<UserField> toFilterQuery( final UserSearchRequest request ) {
        if ( Objects.isNull( request ) || Objects.isNull( request.filters() ) || request.filters().isEmpty() ) {
            return new FilterQuery<>( Collections.emptyList() );
        }

        final List<FilterQuery.Group<UserField>> groups = new ArrayList<>();
        for ( final FilterGroupRequest groupRequest : request.filters() ) {
            final LogicalOperator logicalOperator = this.resolveLogicalOperator( groupRequest.logical() );
            final List<Condition<UserField>> conditions = new ArrayList<>();

            if ( Objects.nonNull( groupRequest.conditions() ) ) {
                for ( final FilterConditionRequest conditionRequest : groupRequest.conditions() ) {
                    conditions.add( this.toCondition( conditionRequest ) );
                }
            }

            if ( !conditions.isEmpty() ) {
                groups.add( new FilterQuery.Group<>( logicalOperator, conditions ) );
            }
        }

        return new FilterQuery<>( groups );
    }

    public PageQuery toPageQuery( final UserSearchRequest request ) {
        if ( Objects.isNull( request ) || Objects.isNull( request.page() ) ) {
            return new PageQuery( null, null );
        }
        return new PageQuery( request.page().page(), request.page().size() );
    }

    private Condition<UserField> toCondition( final FilterConditionRequest request ) {
        final UserField field = UserField.valueOf( request.field().toUpperCase() );
        final Operator operator = Operator.valueOf( request.operator().toUpperCase() );
        final Object value = this.convertValue( field, operator, request );

        return new Condition<>( field, operator, value );
    }

    private Object convertValue( final UserField field, final Operator operator, final FilterConditionRequest request ) {
        if ( operator == Operator.IS_NULL || operator == Operator.IS_NOT_NULL ) {
            return null;
        }

        if ( operator == Operator.IN || operator == Operator.NOT_IN ) {
            final List<Object> values = request.values();
            if ( Objects.isNull( values ) ) return Collections.emptyList();
            return values.stream().map( v -> this.castValue( field, v ) ).toList();
        }

        if ( operator == Operator.BETWEEN ) {
            final List<Object> values = request.values();
            if ( Objects.isNull( values ) || values.size() < 2 ) {
                throw new IllegalArgumentException( "El operador BETWEEN requiere exactamente 2 valores en 'values'" );
            }
            return new Object[]{ this.castValue( field, values.get( 0 ) ), this.castValue( field, values.get( 1 ) ) };
        }

        return this.castValue( field, request.value() );
    }

    private Object castValue( final UserField field, final Object value ) {
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

    private LogicalOperator resolveLogicalOperator( final String value ) {
        if ( Objects.isNull( value ) ) return LogicalOperator.AND;
        return LogicalOperator.valueOf( value.toUpperCase() );
    }
}

