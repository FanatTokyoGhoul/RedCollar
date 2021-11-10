package com.example.redcollar1.configurations;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfiguration {
    private String authServer;
    private String realm;

    @Autowired
    public SwaggerConfiguration(
                          @Value("${keycloak.auth-server-url}") String authServer,
                          @Value("${keycloak.realm}") String realm) {
        this.authServer = authServer;
        this.realm = realm;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        OAuthFlows flows = new OAuthFlows();
        OAuthFlow flow = new OAuthFlow();

        flow.setAuthorizationUrl(authServer + "/realms/" + realm + "/protocol/openid-connect/auth");

        Scopes scopes = new Scopes();
        flow.setScopes(scopes);
        flows = flows.implicit(flow);

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("keycloak",
                        new SecurityScheme().type(SecurityScheme.Type.OAUTH2).flows(flows)))
                .addSecurityItem(new SecurityRequirement().addList("keycloak",
                        Arrays.asList("read", "write")));
    }
}
