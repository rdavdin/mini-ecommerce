package com.example.demo.docs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                            .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                            .info(apiInfo());
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    private Info apiInfo(){
        return new Info()
                    .title("Test: Bearer value with username: user")
                    .description("<strong>Bearer value: </strong>eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoyNjk0ODg1NjM2fQ.qlLIwQmU0GxSnlR7Sy3hSRFDTuXMInznNtIma2AkDHIiRGLJ1RG7yQdK6jXdFMzmCkPE2LfG_Xvyk-N2fStqRg <br><strong>username: user</strong>")
                    .version("Version 1.0");
//                    .contact(apiContact());
                    //.license(apiLicense());
    }

    private Contact apiContact(){
        return new Contact()
                        .name("username: user")
                        .email("email@example.com")
                        .url("https://github.com/rdavdin");
    }

    private License apiLicense(){
        return new License()
                .name("MIT Licence")
                .url("https://opensource.org/licenses/mit-license.php");
    }

}
