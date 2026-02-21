package com.codenaiten.template.server.web.rest.shared.mapper;

import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import com.codenaiten.template.server.web.rest.shared.request.PageRequest;
import com.codenaiten.template.server.web.rest.shared.response.PageResponse;

import java.util.Objects;
import java.util.function.Function;

public interface RestBaseMapper{

    PageQuery toQuery( PageRequest src );

    default <T, R> PageResponse<R> toResponse( final PageInfo<T> src, final Function<T, R> mapper ) {
        if( Objects.isNull( src )) return null;

        return new PageResponse<>(
                src.totalElements(),
                src.totalPages(),
                src.pageNumber(),
                src.pageSize(),
                src.content().stream().map( mapper ).toList()
        );
    }
}


