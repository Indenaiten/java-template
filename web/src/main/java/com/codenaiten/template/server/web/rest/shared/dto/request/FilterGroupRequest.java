package com.codenaiten.template.server.web.rest.shared.dto.request;

import java.util.List;

public record FilterGroupRequest(
        String logical,
        List<FilterConditionRequest> conditions
) {
}

