package com.codenaiten.template.server.infra.persistence.adapter;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;
import com.codenaiten.template.server.infra.persistence.entity.UserJpaEntity;
import com.codenaiten.template.server.infra.persistence.mapper.UserJpaMapper;
import com.codenaiten.template.server.infra.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userRepository;
    private final UserJpaMapper userMapper;

    @Override
    public void save( final User user ){
        this.userRepository.save( this.userMapper.toJpa( user ));
    }

    @Override
    public boolean existsById( final UUID id ){
        return this.userRepository.existsById( id );
    }

    @Override
    public Optional<User> findById( final UUID id ){
        return this.userRepository.findById( id ).map( this.userMapper::toEntity );
    }

    @Override
    public boolean existsByEmail( final String email ){
        return this.userRepository.existsByEmail( email );
    }

    @Override
    public boolean existsByUsername( final String username ){
        return this.userRepository.existsByUsername( username );
    }

    @Override
    public PageInfo<User> search(final String search, final int page, final int size ){
        final Sort sort = Sort.by( Sort.Direction.ASC, "createdAt" );
        final Pageable pageable = PageRequest.of(page, size, sort);
        final org.springframework.data.domain.Page<UserJpaEntity> result = this.userRepository.search( search, pageable );
        List<User> content = result.getContent().stream().map( this.userMapper::toEntity ).toList();
        return new PageInfo<>( result.getTotalElements(), result.getTotalPages(), page, size, content );
    }
}
