package com.codenaiten.template.server.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackages = {
        "com.codenaiten.template.server.boot",
        "com.codenaiten.template.server.app",
        "com.codenaiten.template.server.infra",
        "com.codenaiten.template.server.web"
})
public class ContextConfig {

}
