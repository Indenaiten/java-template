package com.codenaiten.template.server.infra.persistence.adapter;

import com.codenaiten.template.server.app.feature.message.Message;
import com.codenaiten.template.server.app.feature.message.spi.MessageRepository;
import com.codenaiten.template.server.infra.persistence.mapper.MessageJpaMapper;
import com.codenaiten.template.server.infra.persistence.repository.MessageJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MessageRepositoryAdapter implements MessageRepository {

    private final MessageJpaRepository messageRepository;
    private final MessageJpaMapper messageMapper;

    @Override
    public void save( final Message message){
        this.messageRepository.save( this.messageMapper.toJpa( message ));
    }

    @Override
    public Long count() {
        return this.messageRepository.count();
    }

    @Override
    public boolean exists( final Long id ) {
        return this.messageRepository.existsById( id );
    }

    @Override
    public Optional<Message> findById( final Long id ){
        return this.messageRepository.findById( id ).map( this.messageMapper::toEntity );
    }

    @Override
    public List<Message> findAll(){
        final Sort sort = Sort.by( Sort.Direction.DESC, "updatedAt", "updatedAt" );
        return this.messageRepository.findAll( sort ).stream().map( this.messageMapper::toEntity ).toList();
    }
}
