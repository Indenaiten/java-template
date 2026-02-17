package com.codenaiten.template.server.infra.auth.adapter;

import com.codenaiten.template.server.core.shared.spi.PasswordCrypterPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordCrypterAdapter implements PasswordCrypterPort {

    @Override
    public String hash( final String password ) {
        return password.concat( "-hashed" );
    }

    @Override
    public boolean matches( final String password, final String hashedPassword ) {
        return password.concat( "-hashed" ).equals( hashedPassword );
    }
}
