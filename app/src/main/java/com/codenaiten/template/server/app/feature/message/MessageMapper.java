package com.codenaiten.template.server.app.feature.message;

import com.codenaiten.template.server.app.feature.message.dto.MessageInfo;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE )
public interface MessageMapper {

    MessageInfo toMessageInfo( Message message );
}
