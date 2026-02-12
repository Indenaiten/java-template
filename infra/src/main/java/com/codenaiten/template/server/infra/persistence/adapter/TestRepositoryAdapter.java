package com.codenaiten.template.server.infra.persistence.adapter;

import com.codenaiten.template.server.app.feature.test.Test;
import com.codenaiten.template.server.app.feature.test.spi.TestRepository;
import com.codenaiten.template.server.infra.persistence.mapper.TestJpaMapper;
import com.codenaiten.template.server.infra.persistence.repository.TestJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TestRepositoryAdapter implements TestRepository {

    private final TestJpaRepository testRepository;
    private final TestJpaMapper testMapper;

    @Override
    public void save( final Test test ){
        this.testRepository.save( testMapper.toJpa( test ));
    }

    @Override
    public Optional<Test> findById( final Long id ){
        return this.testRepository.findById( id ).map( this.testMapper::toEntity );
    }

    @Override
    public List<Test> findAll(){
        final Sort sort = Sort.by( Sort.Direction.DESC, "createdAt" );
        return this.testRepository.findAll( sort ).stream().map( this.testMapper::toEntity ).toList();
    }
}
