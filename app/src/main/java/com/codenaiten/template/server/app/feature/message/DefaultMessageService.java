package com.codenaiten.template.server.app.feature.message;

import com.codenaiten.template.server.app.feature.message.api.MessageService;
import com.codenaiten.template.server.app.feature.message.command.CreateMessageCommand;
import com.codenaiten.template.server.app.feature.message.dto.MessageInfo;
import com.codenaiten.template.server.app.feature.message.exception.MessageCreationException;
import com.codenaiten.template.server.app.feature.message.exception.MessageNotFoundException;
import com.codenaiten.template.server.app.feature.message.spi.MessageRepository;
import com.codenaiten.template.server.app.feature.message.util.MessageFactory;
import com.codenaiten.template.server.app.feature.message.util.MessageValidator;
import com.codenaiten.template.server.app.feature.user.User;
import com.codenaiten.template.server.app.feature.user.policy.UserAuthPolicy;
import com.codenaiten.template.server.app.feature.user.spi.UserAuthProvider;
import com.codenaiten.template.server.app.feature.user.spi.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultMessageService implements MessageService {

    private final UserAuthProvider userAuthProvider;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Override
    @Transactional
    public void create( final CreateMessageCommand command ){
        log.debug( "Creating Message with text: {}", command.text() );
        final UserAuthPolicy userAuthPolicy = new UserAuthPolicy( this.userAuthProvider, this.userRepository );
        final MessageFactory messageFactory = new MessageFactory();
        final MessageValidator messageValidator = new MessageValidator( this.messageRepository, this.userRepository );

        final User currentUser = userAuthPolicy.getCurrentUser();
        final Message message = messageFactory.create( currentUser.getId(), command.text() );
        messageValidator.owner( message ).text( message ).timestamp( message ).validate();

        try{
            this.messageRepository.save( message );
        }catch( final Exception e ){
            log.error( "Failed to create Message", e );
            throw new MessageCreationException( e );
        }
    }

    @Override
    public MessageInfo get(final Long id) {
        log.debug( "Finding Message with id: {}", id );
        final UserAuthPolicy userAuthPolicy = new UserAuthPolicy( this.userAuthProvider, this.userRepository );

        userAuthPolicy.getCurrentUser();
        final Optional<Message> test = this.messageRepository.findById( id );
        return test.map( this.messageMapper::toMessageInfo).orElseThrow( () -> new MessageNotFoundException( id ));
    }

    @Override
    public List<MessageInfo> all() {
        log.debug( "Finding all Messages" );
        final UserAuthPolicy userAuthPolicy = new UserAuthPolicy( this.userAuthProvider, this.userRepository );

        userAuthPolicy.getCurrentUser();
        return this.messageRepository.findAll().stream().map( this.messageMapper::toMessageInfo).toList();
    }
}
