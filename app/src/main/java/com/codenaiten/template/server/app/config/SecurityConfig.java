package com.codenaiten.template.server.app.config;

import com.codenaiten.template.server.infra.security.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityProperties securityProperties;

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BEANS |------------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Bean
    public DaoAuthenticationProvider daoAuthProvider( final UserDetailsService userDetailsService, final PasswordEncoder passwordEncoder ){
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider( userDetailsService );
        provider.setPasswordEncoder( passwordEncoder );
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager( final AuthenticationConfiguration config ) throws Exception {
        return config.getAuthenticationManager();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}

