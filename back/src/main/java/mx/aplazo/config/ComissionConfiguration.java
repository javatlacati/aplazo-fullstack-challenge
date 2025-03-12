package mx.aplazo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:comissions.properties")
public class ComissionConfiguration {
    @Bean
    public PropertySourcesPlaceholderConfigurer loadProperties() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
