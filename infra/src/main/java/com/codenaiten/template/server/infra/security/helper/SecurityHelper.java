package com.codenaiten.template.server.infra.security.helper;

import com.codenaiten.template.server.infra.security.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityHelper {

    private final SecurityProperties securityProperties;
    private final PathMatcher pathMatcher;

    public boolean isPublicEndpoint( final String method, final String path ) {
        final HttpMethod httpMethod = HttpMethod.valueOf( method.toUpperCase() );
        return this.securityProperties.ignorePaths().stream().anyMatch( p -> pathMatcher.match( p, path )) ||

                ( Objects.equals( HttpMethod.GET, httpMethod ) && this.securityProperties.ignorePathsGet().stream()
                        .anyMatch( p -> pathMatcher.match( p, path ))) ||

                ( Objects.equals( HttpMethod.POST, httpMethod ) && this.securityProperties.ignorePathsPost().stream()
                        .anyMatch( p -> pathMatcher.match( p, path ))) ||

                ( Objects.equals( HttpMethod.PUT, httpMethod ) && this.securityProperties.ignorePathsPut().stream()
                        .anyMatch( p -> pathMatcher.match( p, path ))) ||

                ( Objects.equals( HttpMethod.PATCH, httpMethod ) && this.securityProperties.ignorePathsPatch().stream()
                        .anyMatch( p -> pathMatcher.match( p, path ))) ||

                ( Objects.equals( HttpMethod.DELETE, httpMethod ) && this.securityProperties.ignorePathsDelete().stream()
                        .anyMatch( p -> pathMatcher.match( p, path )));
    }

    public boolean isSecuredEndpoint( final String method, final String path ) {
        return !this.isPublicEndpoint( method, path );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}

