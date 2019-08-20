package com.aquinoa.ziptechtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * OpenAPI UI to easily test the API.
 * 
 * @author allana
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    Docket d = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.basePackage("com.aquinoa.ziptechtask.controllers"))
        .paths(PathSelectors.any()).build();
    return d;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Zip Tech Task").description("API Documentation")
        .version("SNAPSHOT").build();
  }
}
