package com.codenaiten.template.server.core.feature.user.spi;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    void save( User user );
    boolean existsById( UUID id );
    boolean existsByEmail( String email );
    boolean existsByUsername( String username );
    Optional<User> findById( UUID id );
    PageInfo<User> findAll( PageQuery pageQuery );
}
