package com.icbc.patrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：panjiajun
 * @date ：Created on 2019/11/15 16:07
 */
@Configuration
@EnableSwagger2
@Profile({"dev", "test"})
//@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class SwaggerConfig {

    private final String version = "1.0";

    private final String title = "Patrol";

    private final String description = "Patrol项目API文档";

    private final String termsOfServiceUrl = "";

    private final String license = "MIT";

    private final String licenseUrl = "https://mit-license.org/";

    private final Contact contact = new Contact("deplenpan", "https://github.com/deplenpan", "panjiajun07@gmail.com");

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.icbc.patrol.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl(termsOfServiceUrl)
                .description(description)
                .version(version)
                .license(license)
                .licenseUrl(licenseUrl)
                .contact(contact).build();
    }

}
