package com.innowise.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${auth.service.uri}")
    private String authServiceUri;

    @Value("${auth.service.docs}")
    private String authServiceDocs;

    @Value("${user.service.docs}")
    private String userServiceDocs;

    @Value("${order.service.docs}")
    private String orderServiceDocs;

    private static final String API_DOCS = "/v3/api-docs/**";
    private static final String SWAGGER = "/swagger-ui/**";
    private static final String SWAGGER_HTML = "/swagger-ui.html";
    private static final String ACTUATOR = "/actuator/**";
    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .authorizeExchange(ex -> ex
                        .pathMatchers(authServiceUri,
                                authServiceDocs,
                                userServiceDocs,
                                orderServiceDocs,
                                API_DOCS,
                                SWAGGER,
                                SWAGGER_HTML,
                                ACTUATOR).permitAll()
                        .anyExchange().permitAll())
                .build();
    }
}