package com.codenaiten.template.server.app.feature.user.config;

import com.codenaiten.template.server.core.feature.user.UserAuthService;
import com.codenaiten.template.server.core.feature.user.UserService;
import com.codenaiten.template.server.core.feature.user.policy.UserAccessPolicy;
import com.codenaiten.template.server.core.shared.spi.AuthProviderPort;
import com.codenaiten.template.server.core.feature.user.spi.UserMinimumAgeProviderPort;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.spi.PasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class UserConfig{

    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final AuthProviderPort authProvider;
    private final UserMinimumAgeProviderPort userMinimumAgeProvider;

    @Bean
    public UserService userService(){
        return new UserService( this.userMinimumAgeProvider, this.userRepository, this.passwordEncoder );
    }

    @Bean
    public UserAuthService userAuthService(){
        return new UserAuthService( this.userRepository, this.authProvider );
    }

    @Bean
    public UserAccessPolicy userAccessService(){
        return new UserAccessPolicy();
    }

}
