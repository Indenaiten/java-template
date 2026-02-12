package com.codenaiten.template.server.app.feature.test.api;

public interface CreateTestUseCase {

    void run( Request request );

    record Request( String message ) {}
}
