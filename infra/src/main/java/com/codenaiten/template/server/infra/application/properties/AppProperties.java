package com.codenaiten.template.server.infra.application.properties;

import com.codenaiten.template.server.core.feature.user.model.User;
import com.codenaiten.template.server.infra.shared.properties.Properties;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Properties que contiene las propiedades de la aplicación.
 *
 * @see Properties
 */
@Slf4j
@Getter
@Component
@Accessors( fluent = true )
public class AppProperties extends Properties {

    /** Edad mínima del {@link User} para poder acceder al sistema */
    @Value( "${app.user.age.min:18}" )
    private Integer userMinimumAge;

}
