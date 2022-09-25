package br.com.alura.comex.config.swagger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.comex.modelo.Usuario;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.service.AuthorizationScope;


@Configuration
public class SwaggerConfigurations {
  
  @Bean
  public Docket comexApiDocket() {
    
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("br.com.alura.comex"))
      .paths(PathSelectors.any())
      .build()
      .directModelSubstitute(LocalDate.class, String.class)
      .useDefaultResponseMessages(true)
      .securitySchemes(Collections.singletonList(apiKey())) 
      .securityContexts(Collections.singletonList(securityContext()))
      .enableUrlTemplating(true)
      .ignoredParameterTypes(Usuario.class);
  }

  
  private ApiKey apiKey() {
    return new ApiKey("JWT", "Authorization", "header"); 
  }

  private SecurityContext securityContext() { 
    
    return SecurityContext.builder()
      .securityReferences(defaultAuth())
      .operationSelector(selector -> selector.requestMappingPattern()
        .matches("/api/clientes"))
      .build();
      

  } 

  private List<SecurityReference> defaultAuth() { 
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
    authorizationScopes[0] = authorizationScope; 
    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
  }
  
}
