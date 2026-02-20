package com.codenaiten.template.server.web.rest.shared.dto.request;

import java.util.List;

public record FilterRequest(
        String logical,
        List<Condition> conditions
) {
    public record Condition(
            String field,
            String operator,
            Object value,
            List<Object> values
    ) {
    }
}

