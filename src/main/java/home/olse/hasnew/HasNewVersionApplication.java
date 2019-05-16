package home.olse.hasnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Logger;

@SpringBootApplication
public class HasNewVersionApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(HasNewVersionApplication.class, args);
    }

    @Bean
    public Logger getLogger(){
        return Logger.getLogger(this.getClass().getName());
    }
}
