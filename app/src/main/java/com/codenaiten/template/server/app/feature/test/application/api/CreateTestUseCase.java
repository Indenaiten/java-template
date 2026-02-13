package com.codenaiten.template.server.app.feature.test.application.api;

public interface CreateTestUseCase {

    void run( Request request );

    record Request( String message ) {}
}
