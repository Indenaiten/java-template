package com.codenaiten.template.server.app.feature.user.config;

import com.codenaiten.template.server.core.feature.user.policy.UserAccessPolicy;
import com.codenaiten.template.server.core.feature.user.UserAuthService;
import com.codenaiten.template.server.core.feature.user.UserService;
import com.codenaiten.template.server.core.feature.user.spi.UserAuthProviderPort;
import com.codenaiten.template.server.core.feature.user.spi.UserPropertiesPort;
import com.codenaiten.template.server.core.feature.user.spi.UserRepositoryPort;
import com.codenaiten.template.server.core.shared.spi.PasswordCrypterPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class UserConfig{

    private final UserPropertiesPort userProperties;
    private final UserRepositoryPort userRepository;
    private final UserAuthProviderPort userAuthProvider;
    private final PasswordCrypterPort passwordCrypter;

    @Bean
    public UserService userService(){
        return new UserService( this.userProperties, this.userRepository, this.passwordCrypter );
    }

    @Bean
    public UserAuthService userAuthService(){
        return new UserAuthService( this.userRepository, this.userAuthProvider );
    }

    @Bean
    public UserAccessPolicy userAccessService(){
        return new UserAccessPolicy();
    }

}
