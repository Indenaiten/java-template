package com.codenaiten.template.server.web.rest.shared.mapper;

import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.web.rest.shared.dto.request.PageRequest;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper( componentModel = "spring",
         unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface RestMapper{

    default <T> Optional<T> toOptional( T src ){
        return Optional.ofNullable( src );
    }

    PageQuery toQuery( PageRequest src );
}


