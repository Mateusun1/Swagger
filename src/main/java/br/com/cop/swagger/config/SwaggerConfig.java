package br.com.cop.swagger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("DB Rank API")
                .description("")
                .contact(new Contact().name("DB").url("https://db.tec" + ".br" + "/"))
                .version("1.0.0")
                .license(new License())
                .termsOfService(""));
    }
}

