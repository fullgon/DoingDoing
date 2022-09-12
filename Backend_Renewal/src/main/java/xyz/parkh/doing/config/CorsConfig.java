package xyz.parkh.doing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // JS 로 요청 가능?
        config.addAllowedOrigin("*"); // ip
        config.addAllowedHeader("*"); // header
        config.addAllowedMethod("*"); // Http Method
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }

}
