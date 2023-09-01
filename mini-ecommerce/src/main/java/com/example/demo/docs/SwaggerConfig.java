package com.example.demo.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo(){
        return new Info()
                    .title("This is a title")
                    .description("This is a description")
                    .version("Version 1.0")
                    .contact(apiContact())
                    .license(apiLicense());
    }

    private Contact apiContact(){
        return new Contact()
                        .name("NAME PUT HERE")
                        .email("email@example.com")
                        .url("https://github.com/rdavdin");
    }

    private License apiLicense(){
        return new License()
                .name("MIT Licence")
                .url("https://opensource.org/licenses/mit-license.php");
    }

}
