package com.codenaiten.template.server.web.rest.shared.api.response;

import java.util.List;

public record PageResponse<T>(
        Long totalElements,
        Integer totalPages,
        Integer pageNumber,
        Integer pageSize,
        List<T> content
) {
}
