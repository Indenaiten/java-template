package com.codenaiten.template.server.app.config;

import com.codenaiten.template.server.infra.security.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.NullSecurityContextRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final SecurityProperties securityProperties;

// ------------------------------------------------------------------------------------------------------------------ \\
// ---| BEANS |------------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration cfg = new CorsConfiguration();

        if( this.securityProperties.corsAllowedOrigins().contains( "*" ))
            cfg.setAllowedOriginPatterns( this.securityProperties.corsAllowedOrigins() );
        else cfg.setAllowedOrigins( this.securityProperties.corsAllowedOrigins());

        cfg.setAllowedMethods( this.securityProperties.corsAllowedMethods() );
        cfg.setAllowedHeaders( this.securityProperties.corsAllowedHeaders() );
        cfg.setExposedHeaders( this.securityProperties.corsExposedHeaders() );

        this.securityProperties.corsAllowCredentials().ifPresent( cfg::setAllowCredentials );

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration( "/**", cfg );
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http, final CorsConfigurationSource corsConfigurationSource ) throws Exception {
        http.csrf( CsrfConfigurer::disable ) // Disable CSRF
                // CORS Config
                .cors( cors -> cors.configurationSource( corsConfigurationSource ))
                // Set session management to stateless
                .sessionManagement( sm -> sm.sessionCreationPolicy( SessionCreationPolicy.STATELESS ))
                // Not save any SecurityContext in the HTTP Session
                .securityContext( sc -> sc.securityContextRepository( new NullSecurityContextRepository() ))
                // Not save requests to redirections in cache
                .requestCache( rc -> rc.requestCache( new NullRequestCache() ))
                // Disable mechanisms of authentication with state
                .formLogin( AbstractHttpConfigurer::disable )
                .httpBasic( AbstractHttpConfigurer::disable )
                .logout( AbstractHttpConfigurer::disable )
                .rememberMe( AbstractHttpConfigurer::disable )
                // Secured Endpoints Config
                .authorizeHttpRequests(auth -> auth
                        // Set public paths
                        .requestMatchers( this.securityProperties.ignorePaths().toArray( new String[0] )).permitAll()
                        .requestMatchers( HttpMethod.GET, this.securityProperties.ignorePathsGet().toArray( new String[0] )).permitAll()
                        .requestMatchers( HttpMethod.POST, this.securityProperties.ignorePathsPost().toArray( new String[0] )).permitAll()
                        .requestMatchers( HttpMethod.PUT, this.securityProperties.ignorePathsPut().toArray( new String[0] )).permitAll()
                        .requestMatchers( HttpMethod.PATCH, this.securityProperties.ignorePathsPatch().toArray( new String[0] )).permitAll()
                        .requestMatchers( HttpMethod.DELETE, this.securityProperties.ignorePathsDelete().toArray( new String[0] )).permitAll()
                        .anyRequest().authenticated() // Set all other paths to authenticated
                );
                // Set JWT Filter
                //.addFilterBefore( this.tokenJwtFilter, UsernamePasswordAuthenticationFilter.class )
                // Set Exception Handlers
                //.exceptionHandling(ex -> ex
                        //.authenticationEntryPoint( this.authenticationEntryPoint )
                        //.accessDeniedHandler( this.accessDeniedHandler ));
        return http.build();
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}

