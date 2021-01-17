package br.com.brewers.cervapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    private final BuildProperties properties;

    @Autowired
    public SwaggerConfig(BuildProperties properties) {
        this.properties = properties;
    }

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title(properties.getName())
                        .description("API de Cervejas encontradas no Brasil")
                        .version(properties.getVersion())
                        .contact(new Contact("Brewers", "https://github.com/mtafrias/", "mtafrias@gmail.com"))
                        .license("MIT License")
                        .build()
                );
    }
}
