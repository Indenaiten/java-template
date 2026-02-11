package com.codenaiten.template.server.app.feature.message.api;

import com.codenaiten.template.server.app.feature.message.command.CreateMessageCommand;
import com.codenaiten.template.server.app.feature.message.dto.MessageInfo;

import java.util.List;

public interface MessageService {

    void create( CreateMessageCommand command );

    MessageInfo get(Long id );

    List<MessageInfo> all();
}
