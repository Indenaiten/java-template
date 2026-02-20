package com.codenaiten.template.server.web.rest.shared.dto.request;

import java.util.List;

public record FilterConditionRequest(
        String field,
        String operator,
        Object value,
        List<Object> values
) {
}

