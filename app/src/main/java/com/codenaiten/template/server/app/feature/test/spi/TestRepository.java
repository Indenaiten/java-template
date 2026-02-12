package com.codenaiten.template.server.app.feature.test.spi;

import com.codenaiten.template.server.app.feature.test.Test;

import java.util.List;
import java.util.Optional;

public interface TestRepository {

    void save( Test test );
    Optional<Test> findById( Long id );
    List<Test> findAll();
}
