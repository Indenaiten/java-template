package com.codenaiten.template.server.core.shared.spi;

public interface PasswordCrypterPort {

    String hash( String password );
    boolean matches( String password, String hashedPassword );
}