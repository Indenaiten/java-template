package com.codenaiten.template.server.app.feature.message.util;

import com.codenaiten.template.server.app.feature.message.Message;
import com.codenaiten.template.server.app.feature.message.spec.MessageIdValidSpec;
import com.codenaiten.template.server.app.feature.message.spec.MessageOwnerValidSpec;
import com.codenaiten.template.server.app.feature.message.spec.MessageTextValidSpec;
import com.codenaiten.template.server.app.feature.message.spec.MessageTimestampValidSpec;
import com.codenaiten.template.server.app.feature.message.spi.MessageRepository;
import com.codenaiten.template.server.app.feature.user.spi.UserRepository;
import com.codenaiten.template.server.app.shared.spec.Specification;
import com.codenaiten.template.server.app.shared.util.BaseValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessageValidator extends BaseValidator {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageValidator id( final Message message ){
        final Specification<Message> spec = new MessageIdValidSpec( this.messageRepository );
        if( spec.not().test( message )) errors.add( "Invalid id: %d".formatted( message.getId() ));
        return this;
    }

    public MessageValidator owner( final Message message ){
        final Specification<Message> spec = new MessageOwnerValidSpec( this.userRepository );
        if( spec.not().test( message )) errors.add( "Invalid owner: %s".formatted( message.getOwner() ));
        return this;
    }

    public MessageValidator text(final Message message ){
        final Specification<Message> spec = new MessageTextValidSpec();
        if( spec.not().test( message )) this.errors.add( "Invalid text:  %s".formatted( message.getText() ));
        return this;
    }

    public MessageValidator timestamp(final Message message ){
        final Specification<Message> spec = new MessageTimestampValidSpec();
        if( spec.not().test( message ))
            this.errors.add( "Invalid timestamp: { createdAt: %s, updatedAt: %s }".formatted( message.getCreatedAt(), message.getUpdatedAt() ));
        return this;
    }
}
