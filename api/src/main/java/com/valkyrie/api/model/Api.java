package com.valkyrie.api.model;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Api {

    private RouterFunction<ServerResponse> post(String name, String initialName, String finalName) {
        return route(name).POST(finalName, http()).before(uri(initialName)).build();
    }

    private RouterFunction<ServerResponse> get(String name, String initialName, String finalName) {
        return route(name).GET(finalName, http()).before(uri(initialName)).build();
    }

    private RouterFunction<ServerResponse> delete(String name, String initialName, String finalName) {
        return route(name).DELETE(finalName, http()).before(uri(initialName)).build();
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return post("authentication_post", "http://localhost:8081/", "/authentication/**")
            .and(
                delete("authentication_delete", "http://localhost:8081/", "/authentication/**")
            );
    }

}
