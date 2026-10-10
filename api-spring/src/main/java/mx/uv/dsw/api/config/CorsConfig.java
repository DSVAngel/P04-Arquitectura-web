package mx.uv.dsw.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Habilita CORS acotado al origen del cliente Angular en desarrollo (RNF06: despliegue local). */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${ANGULAR_ORIGIN:http://localhost:4200}")
    private String angularOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(angularOrigin)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
