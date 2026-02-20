package com.codenaiten.template.server.web.rest.feature.user.converter;

import com.codenaiten.template.server.core.feature.user.dto.UserFilterField;
import com.codenaiten.template.server.core.feature.user.dto.query.SearchUserQuery;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.UserSearchRequest;
import com.codenaiten.template.server.web.rest.shared.dto.converter.FilterRequestConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Convierte un {@link UserSearchRequest} de la capa REST en un {@link SearchUserQuery} del dominio.
 */
@Component
public class UserSearchRequestConverter {

    /**
     * Convierte el request completo en un query de dominio.
     *
     * @param request {@link UserSearchRequest} La petición REST.
     *
     * @return {@link SearchUserQuery} El query del dominio equivalente.
     */
    public SearchUserQuery toQuery( final UserSearchRequest request ) {
        return new SearchUserQuery( toFilterQuery( request ), toPageQuery( request ) );
    }

    /**
     * Convierte el filtro del request en un {@link FilterQuery} del dominio.
     *
     * @param request {@link UserSearchRequest} La petición REST.
     *
     * @return {@link FilterQuery} El filtro del dominio equivalente.
     */
    public FilterQuery toFilterQuery( final UserSearchRequest request ) {
        if ( Objects.isNull( request ) ) {
            return FilterRequestConverter.convert( null, UserFilterField.class );
        }
        return FilterRequestConverter.convert( request.filter(), UserFilterField.class );
    }

    /**
     * Convierte la paginación del request en un {@link PageQuery} del dominio.
     *
     * @param request {@link UserSearchRequest} La petición REST.
     *
     * @return {@link PageQuery} La paginación del dominio equivalente.
     */
    public PageQuery toPageQuery( final UserSearchRequest request ) {
        if ( Objects.isNull( request ) || Objects.isNull( request.page() ) ) {
            return new PageQuery( null, null );
        }
        return new PageQuery( request.page().number(), request.page().size() );
    }
}
