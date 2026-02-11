package com.codenaiten.template.server.app.feature.message.command;

import lombok.NonNull;

public record CreateMessageCommand( @NonNull String text ){
}
