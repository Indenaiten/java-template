package com.codenaiten.template.server.core.shared.dto.result;

import java.util.List;

public record PageInfo<T>(
        Long totalElements,
        Integer totalPages,
        Integer pageNumber,
        Integer pageSize,
        List<T> content
) {
}
