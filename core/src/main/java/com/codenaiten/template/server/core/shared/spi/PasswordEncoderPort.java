package com.codenaiten.template.server.core.shared.spi;

public interface PasswordEncoderPort {

    String hash( String password );
    boolean matches( String password, String hashedPassword );
}