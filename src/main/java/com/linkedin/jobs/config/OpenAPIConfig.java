package com.linkedin.jobs.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Contact contact = new Contact();
		contact.setEmail("test@vinaysharma.com");
		contact.setName("Vinay Sharma");
		contact.setUrl("https://www.vinaysharma.com");

		License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

		Info info = new Info().title("Linkedin Jobs Crawler APIs").version("1.0").contact(contact)
				.description("This APIs exposes endpoints to securely access the jobs data from linkedin.")
				.termsOfService("https://www.upcomingdomain.com/terms").license(mitLicense);

		return new OpenAPI()
				.components(new Components().addSecuritySchemes("Authorization",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.security(List.of(new SecurityRequirement().addList("Authorization"))).info(info);
	}

}