package com.codenaiten.template.server.boot;

import com.codenaiten.template.server.app.AppContextConfig;
import com.codenaiten.template.server.infra.InfraContextConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ AppContextConfig.class, InfraContextConfig.class })
public class RestServerApplication {

    public static void main( final String[] args ) {
        SpringApplication.run( RestServerApplication.class, args );
    }
}
