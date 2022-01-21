package co.com.robin.feedback.infrastructure.entrypoints.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String TITLE = "Polls for Feedback Microservice";
    private static final String VERSION = "1.0.0";
    private static final String DESCRIPTION = "Allows to receive anonymous feedback from the customers of a given restaurant.";

    @Bean
    public OpenAPI pollOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(TITLE)
                        .version(VERSION)
                        .description(DESCRIPTION));
    }

}
