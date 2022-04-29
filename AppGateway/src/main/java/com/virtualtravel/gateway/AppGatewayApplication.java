package com.virtualtravel.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class AppGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGatewayApplication.class,args);
    }

    @Bean
    public RouteLocator customRouteLocaltor(RouteLocatorBuilder builder){
        return builder.routes()
                .route("web", r -> r.path("/api/v0/**").uri("lb://BACKWEB/"))
                .route("company", r->r.path("/empresa/v0/**").uri("lb://BACKEMPRESA/"))
                .build();
    }
}
