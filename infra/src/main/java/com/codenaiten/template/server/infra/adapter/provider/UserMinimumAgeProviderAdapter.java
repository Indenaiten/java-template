package com.codenaiten.template.server.infra.adapter.provider;

import com.codenaiten.template.server.core.feature.user.spi.UserMinimumAgeProviderPort;
import com.codenaiten.template.server.infra.application.properties.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserMinimumAgeProviderAdapter implements UserMinimumAgeProviderPort{

    private final AppProperties appProperties;

    @Override
    public int getMinimumAge() {
        return this.appProperties.userMinimumAge();
    }
}
