package mx.aplazo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
//    @Bean
//    public GroupedOpenApi customOpenApi() {
//        return GroupedOpenApi.builder()
//               .group("aplazo")
//               .pathsToMatch("/v1/**")
//               .build();
//    }

    @Bean
    public OpenAPI aplazoOpenApi() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Aplazo BNPL API")
                .version("0.0.1")
                .description(
                    "### Minimal BNPL API\n"
                        + "\n"
                        + "    Allows for customer and loan creation."));
    }
}
