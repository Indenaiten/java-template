package com.codenaiten.template.server.infra.persistence.repository;

import com.codenaiten.template.server.infra.persistence.entity.UserJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {

    boolean existsByEmail( String email );
    boolean existsByUsername( String username );

    @Query("SELECT u FROM UserJpaEntity u " +
            "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(u.surname) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<UserJpaEntity> search( @Param("search") String search, Pageable pageable);
}
