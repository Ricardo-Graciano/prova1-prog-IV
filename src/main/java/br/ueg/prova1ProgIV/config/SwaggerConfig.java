package br.ueg.prova1ProgIV.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	// uri -> /swagger-ui/index.html
	private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
				.title("Primeira Prova de Programação IV")
				.description("Este projeto engloba um simples e-commerce permitindo o cadastro de usuários, produtos e o registro de vendas. \n\n Github: https://github.com/Ricardo-Graciano/prova1-prog-IV")
				//.description("Github: https://github.com/Ricardo-Graciano/prova1-prog-IV")
				.version("0.0.1")
				//.contact(new Contact("Ricardo Graciano", "ueg.br", "igracianoooric@icloud.com"))
				//.contact(new Contact("Gabriel Gomes", "ueg.br", "corinthiaswwyy@gmail.com"))
				.license("Apache License version 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.build();
    }
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .enable(true)
                .select()
                .paths(PathSelectors.any())
                .build();
    }
}