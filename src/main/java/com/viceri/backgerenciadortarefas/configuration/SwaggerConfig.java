package com.viceri.backgerenciadortarefas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI springRedeSocialOpenAPI() {
		return new OpenAPI()
		.info(new Info()
		.title("Gerenciador de tarefas")
		.description("Teste back-end")
		.version("v0.0.1")
		.license(new License()
		.name("Viceri")
		.url("https://viceri.com.br/"))
		.contact(new Contact()
		.name("Thayrine Renata dos Santos")
		.url("https://github.com/thayrinerenata")
		.email("thayrinerenata@gmail.com")))
		.externalDocs(new ExternalDocumentation()
		.description("GitHub")
		.url(""));
	}
	
	@Bean
	public OpenApiCustomiser customeGlobalHeaderOpenApiCustomiser() {
		return openApi -> {
		openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
		ApiResponses apiResponses = operation.getResponses();
		apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
		apiResponses.addApiResponse("201", createApiResponse("Objeto Salvo!"));
		apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
		apiResponses.addApiResponse("400", createApiResponse("Erro de Requisição!"));
		apiResponses.addApiResponse("401", createApiResponse("Acesso não Autorizado!"));
		apiResponses.addApiResponse("404", createApiResponse("Objeto não Encontrado!"));
		apiResponses.addApiResponse("500", createApiResponse("Erro Interno no Servidor!"));
		}));
		};
	}
	
	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
	

}
