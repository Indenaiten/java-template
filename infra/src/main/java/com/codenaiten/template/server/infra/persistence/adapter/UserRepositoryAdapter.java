package com.codenaiten.template.server.infra.persistence.adapter;

import com.codenaiten.template.server.app.feature.user.User;
import com.codenaiten.template.server.app.feature.user.spi.UserRepository;
import com.codenaiten.template.server.infra.persistence.mapper.UserJpaMapper;
import com.codenaiten.template.server.infra.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userRepository;
    private final UserJpaMapper userMapper;

    @Override
    public boolean exists( final UUID id ) {
        return this.userRepository.existsById( id );
    }

    @Override
    public Optional<User> findById( final UUID id ) {
        return this.userRepository.findById( id ).map( this.userMapper::toEntity );
    }
}
