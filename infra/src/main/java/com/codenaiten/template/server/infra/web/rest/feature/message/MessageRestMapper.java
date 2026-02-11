package com.codenaiten.template.server.infra.web.rest.feature.message;

import com.codenaiten.template.server.app.feature.message.command.CreateMessageCommand;
import com.codenaiten.template.server.app.feature.message.dto.MessageInfo;
import com.codenaiten.template.server.infra.web.rest.feature.message.dto.input.CreateMessageInput;
import com.codenaiten.template.server.infra.web.rest.feature.message.dto.output.MessageInfoOutput;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface MessageRestMapper {

    CreateMessageCommand toCommand( CreateMessageInput src );
    MessageInfoOutput toOutput( MessageInfo src );
}
