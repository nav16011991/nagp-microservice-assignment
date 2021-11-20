package com.nagarro.nagp.applicationgateway.config;

import com.nagarro.nagp.applicationgateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("utility-service", r -> r.path("/utility/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://utility-service"))

                .route("order-service", r -> r.path("/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://order-service"))

                .route("provider-service", r -> r.path("/provider/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://provider-service"))

                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .build();
    }


}
