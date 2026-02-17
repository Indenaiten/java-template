package com.codenaiten.template.server.core.shared.dto;

import java.util.List;

public record Page<T>(
        Long totalElements,
        Integer totalPages,
        Integer pageNumber,
        Integer pageSize,
        List<T> content
) {
}
