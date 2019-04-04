package home.olse.hasnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Logger;

@SpringBootApplication
@EnableWebMvc
@Configuration
public class HasNewVersionApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(HasNewVersionApplication.class, args);
    }

    @Bean
    public Logger getLogger() {
        return Logger.getLogger(HasNewVersionApplication.class.getName());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.TEXT_HTML)
                .favorPathExtension(false)
                .favorParameter(false);
    }
}
