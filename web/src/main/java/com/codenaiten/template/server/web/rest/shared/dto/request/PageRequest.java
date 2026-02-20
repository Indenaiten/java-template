package com.codenaiten.template.server.web.rest.shared.dto.request;

public record PageRequest(
        Integer page,
        Integer size
) {
}

