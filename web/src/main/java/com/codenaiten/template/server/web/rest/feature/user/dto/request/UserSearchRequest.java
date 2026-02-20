package com.codenaiten.template.server.web.rest.feature.user.dto.request;

import com.codenaiten.template.server.web.rest.shared.dto.request.FilterRequest;
import com.codenaiten.template.server.web.rest.shared.dto.request.PageRequest;

public record UserSearchRequest(
        PageRequest page,
        FilterRequest filter
) {
}

