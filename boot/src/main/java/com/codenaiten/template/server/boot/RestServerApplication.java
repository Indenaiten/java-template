package com.codenaiten.template.server.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServerApplication {

    public static void main( final String[] args ) {
        SpringApplication.run( RestServerApplication.class, args );
    }
}
