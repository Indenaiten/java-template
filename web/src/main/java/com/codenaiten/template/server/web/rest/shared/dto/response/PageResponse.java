package com.codenaiten.template.server.web.rest.shared.dto.response;

import java.util.List;

public record PageResponse<T>(
        Long totalElements,
        Integer totalPages,
        Integer pageNumber,
        Integer pageSize,
        List<T> content
) {
}
