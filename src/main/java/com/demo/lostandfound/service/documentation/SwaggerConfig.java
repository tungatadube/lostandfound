package com.demo.lostandfound.service.documentation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	static final Contact DEFAULT_CONTACT = new Contact("Mduduzi Dube", "", "tungatadube@gmail.com");

	static ApiInfo apiInfo(){
		return new ApiInfoBuilder().title("Document Service API").version("0.0.1").contact(DEFAULT_CONTACT).description(
				"A service for managing lost and found property."). build();
	}

	static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json",
			"application/xml"));



	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).consumes(DEFAULT_PRODUCES_AND_CONSUMES).produces(DEFAULT_PRODUCES_AND_CONSUMES);

	}


}
