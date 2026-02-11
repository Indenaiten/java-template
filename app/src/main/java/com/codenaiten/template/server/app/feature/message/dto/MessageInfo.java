package com.codenaiten.template.server.app.feature.message.dto;

import java.time.LocalDateTime;

public record MessageInfo( Long id,
                           String owner,
                           String text,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt
){
}
