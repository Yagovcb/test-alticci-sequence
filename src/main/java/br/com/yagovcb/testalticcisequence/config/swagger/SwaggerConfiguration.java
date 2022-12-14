package com.praxis.praxis_users.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String URL = "https://brasil.praxisglobe.com/";
    private static final String EMAIL = "brasil@praxisglobe.com";
    private static final String PRAXIS_NAME = "PRAXIS Tecnologia";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(informacoesApi())
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.praxis.praxis_users.application.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo informacoesApi() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("USERS - API");
        apiInfoBuilder.description("API Rest para disponibilziar os serviços de gerenciamento de usuarios");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termos de uso: Todos os direitos reservados - " + PRAXIS_NAME);
        apiInfoBuilder.license(PRAXIS_NAME);
        apiInfoBuilder.licenseUrl(URL);
        apiInfoBuilder.contact(new Contact(PRAXIS_NAME, URL, EMAIL));
        return apiInfoBuilder.build();
    }
}