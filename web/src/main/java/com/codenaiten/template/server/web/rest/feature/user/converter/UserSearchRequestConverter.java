package com.codenaiten.template.server.web.rest.feature.user.converter;

import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.web.rest.feature.user.dto.request.UserSearchRequest;
import com.codenaiten.template.server.web.rest.shared.dto.converter.FilterRequestConverter;

import java.util.Objects;

public class UserSearchRequestConverter {

    public FilterQuery<UserField> toFilterQuery( final UserSearchRequest request ) {
        if ( Objects.isNull( request ) ) {
            return FilterRequestConverter.convert( null, UserField.class );
        }
        return FilterRequestConverter.convert( request.filter(), UserField.class );
    }

    public PageQuery toPageQuery( final UserSearchRequest request ) {
        if ( Objects.isNull( request ) || Objects.isNull( request.page() ) ) {
            return new PageQuery( null, null );
        }
        return new PageQuery( request.page().number(), request.page().size() );
    }
}

