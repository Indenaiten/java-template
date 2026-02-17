package com.codenaiten.template.server.infra.properties.adapter;

import com.codenaiten.template.server.core.feature.user.spi.UserPropertiesPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserPropertiesAdapter implements UserPropertiesPort {

    @Override
    public int getMinimumAge() {
        return 18;
    }
}
