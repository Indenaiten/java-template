package com.codenaiten.template.server.infra.web.rest.feature.message.dto.output;

import java.util.UUID;

public record MessageInfoOutput( Long id, UUID owner, String text ) {
}
