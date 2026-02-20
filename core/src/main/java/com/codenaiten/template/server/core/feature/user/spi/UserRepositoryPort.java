package com.codenaiten.template.server.core.feature.user.spi;

import com.codenaiten.template.server.core.feature.user.dto.UserField;
import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.core.shared.dto.query.FilterQuery;
import com.codenaiten.template.server.core.shared.dto.query.PageQuery;
import com.codenaiten.template.server.core.shared.dto.result.PageInfo;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    void save( User user );
    boolean existsById( UUID id );
    Optional<User> findById( UUID id );
    boolean existsByEmail( String email );
    boolean existsByUsername( String username );
    PageInfo<User> search( FilterQuery<UserField> filterQuery, PageQuery pageQuery );
}
