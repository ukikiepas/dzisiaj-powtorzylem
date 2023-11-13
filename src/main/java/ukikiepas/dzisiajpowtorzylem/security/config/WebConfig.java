package ukikiepas.dzisiajpowtorzylem.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    private static final long MAX_AGE_SECS = 3600;

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // Adresy, z których przyjmowane są żądania
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // Dozwolone metody HTTP
                        .allowedHeaders("Authorization", "Content-Type", "Accept") // Dozwolone nagłówki
                        .exposedHeaders("Authorization", "Refresh-Token") // Nagłówki, które mogą być odczytane przez przeglądarkę po odpowiedzi serwera
                        .maxAge(MAX_AGE_SECS); // Maksymalny czas życia preflight request (w sekundach)
            }
        };
    }

}