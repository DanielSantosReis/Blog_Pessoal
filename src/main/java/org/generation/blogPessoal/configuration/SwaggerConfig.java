package org.generation.blogPessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Projeto Meu Blog Pessoal")
						.description("Projeto Meu Blog Pessoal - Generation Brasil").version("v0.0.1")
						.license(new License().name("Meu Blog Pessoal").url("<https://brazil.generation.org/>"))
						.contact(new Contact().name("Daniel Reis").url("https://github.com/DanielSantosReis")
								.email("kr.danielreis@gmail.com")))
				.externalDocs(new ExternalDocumentation().description("Github")
						.url("https://github.com/conteudoGeneration/"));
	}

	@Bean
	public OpenApiCustomiser customerGlobalResponseStatus() {
		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
				ApiResponses api = operation.getResponses();

				api.addApiResponse("200", createApiResponse("Sucess!"));
				api.addApiResponse("201", createApiResponse("Created!"));
				api.addApiResponse("400", createApiResponse("Request error!"));
				api.addApiResponse("401", createApiResponse("Not authorized!"));
				api.addApiResponse("500", createApiResponse("Internal server Error!"));
			}));

		};

	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}

}