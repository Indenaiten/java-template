package com.codenaiten.template.server.infra.web.rest.api.shared.response;

import java.util.List;

public record PageResponse<T>(
        Long totalElements,
        Integer totalPages,
        Integer pageNumber,
        Integer pageSize,
        List<T> content
) {
}
