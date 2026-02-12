package com.codenaiten.template.server.infra.persistence.repository;

import com.codenaiten.template.server.infra.persistence.entity.TestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestJpaRepository extends JpaRepository<TestJpaEntity, Long> {
}
