package mx.aplazo.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public GroupedOpenApi customOpenApi() {
        return GroupedOpenApi.builder()
               .group("aplazo")
               .pathsToMatch("/v1/**")
               .build();
    }
}
