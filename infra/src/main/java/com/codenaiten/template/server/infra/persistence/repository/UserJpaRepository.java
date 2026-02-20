package com.codenaiten.template.server.infra.persistence.repository;

import com.codenaiten.template.server.infra.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID>, JpaSpecificationExecutor<UserJpaEntity> {

    boolean existsByEmail( String email );
    boolean existsByUsername( String username );

}
