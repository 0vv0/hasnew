package home.olse.hasnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.logging.Logger;

@SpringBootApplication
@EnableWebMvc
public class HasNewVersionApplication {
    public static void main(String[] args) {
        SpringApplication.run(HasNewVersionApplication.class, args);
    }

    @Bean
    public Logger getLogger(){
        return Logger.getLogger(HasNewVersionApplication.class.getName());
    }
}
