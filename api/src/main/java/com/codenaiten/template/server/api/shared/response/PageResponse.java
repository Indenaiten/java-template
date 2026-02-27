package com.codenaiten.template.server.api.shared.response;

import java.util.List;

public record PageResponse<T>(
        Long totalElements,
        Integer totalPages,
        Integer pageNumber,
        Integer pageSize,
        List<T> content
) {
}
