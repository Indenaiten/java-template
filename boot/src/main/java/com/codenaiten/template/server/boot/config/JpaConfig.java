package com.codenaiten.template.server.boot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan( basePackages = "com.codenaiten.template.server.infra.persistence.entity" )
@EnableJpaRepositories( basePackages = "com.codenaiten.template.server.infra.persistence.repository" )
public class JpaConfig {

}


