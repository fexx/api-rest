package br.com.fernandoevangelista.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Documentação de api rest
 * 
 * Para acessar o swagger: {endereco}/swagger-ui.html
 * 
 *
 */
@Configuration
@EnableSwagger2
public class SwagerConfig {
	
	@Bean
	public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.fernandoevangelista.controller"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
            .globalResponseMessage(RequestMethod.POST, responseMessageForGET())
            .globalResponseMessage(RequestMethod.PUT, responseMessageForGET())
            .globalResponseMessage(RequestMethod.DELETE, responseMessageForGET())
            .apiInfo(apiInfo());
    }
	
	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("API - Manipulação de carros")
			.description("API para manipulação de carros")
			.license("Fernando Evangelista")
            .licenseUrl("https://www.linkedin.com/in/fernando-evangelista-101480103/")
            .contact(new Contact("Fernando Evangelista", "https://www.linkedin.com/in/fernando-evangelista-101480103/", "privado"))
			.version("1.0.0")
			.build();
	}
	
	private List<ResponseMessage> responseMessageForGET() {
		return Arrays.asList(new ResponseMessageBuilder()
	            .code(500).message("500 message").responseModel(new ModelRef("Error")).build());
	}

}
