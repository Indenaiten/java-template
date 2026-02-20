package com.codenaiten.template.server.web.rest.feature.user.dto.request;

import com.codenaiten.template.server.web.rest.shared.dto.request.FilterGroupRequest;
import com.codenaiten.template.server.web.rest.shared.dto.request.PageRequest;

import java.util.List;

public record UserSearchRequest(
        PageRequest page,
        List<FilterGroupRequest> filters
) {
}

