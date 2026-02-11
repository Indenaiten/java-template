package com.codenaiten.template.server.infra.persistence.repository;

import com.codenaiten.template.server.infra.persistence.entity.MessageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageJpaRepository extends JpaRepository<MessageJpaEntity, Long> {
}
