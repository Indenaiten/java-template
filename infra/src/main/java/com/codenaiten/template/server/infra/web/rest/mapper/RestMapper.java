package com.codenaiten.template.server.infra.web.rest.mapper;

import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import com.codenaiten.template.server.infra.web.rest.shared.dto.request.PageRequest;
import com.codenaiten.template.server.infra.web.rest.shared.dto.response.PageResponse;
import org.mapstruct.Mapper;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Mapper( componentModel = "spring",
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface RestMapper{

    PageQuery toQuery( PageRequest src );

    default <T> T unwrapOptional( final Optional<T> src ){
        return src.orElse( null );
    }

    default <T> Optional<T> toOptional( final T src ){
        return Optional.ofNullable( src );
    }

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


