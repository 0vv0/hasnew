package home.olse.hasnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.logging.Level;
import java.util.logging.Logger;


@EnableWebMvc
@Configuration
public class Config implements WebMvcConfigurer {
    @Autowired
    private Logger logger;
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.TEXT_HTML)
                .favorPathExtension(false)
                .favorParameter(false);
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public static String handleHttpMediaTypeNotAcceptableException() {
        return "acceptable MIME type:" + MediaType.TEXT_HTML_VALUE;
    }

    @Bean
    public static Logger getLogger() {
        return Logger.getLogger(HasNewVersionApplication.class.getName());
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocations(new ClassPathResource("application.properties"));
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        return propertyPlaceholderConfigurer;
    }

    @Value("${dpd.path}")
    private String dpdPath;

    @Value("${apps.path}")
    private String appsPath;

    public String getDpdPath() {
        return dpdPath;
    }

    public void setDpdPath(String dpdPath) {
        logger.log(Level.INFO, "dpd path = " + dpdPath);
        this.dpdPath = dpdPath;
    }

    public String getAppsPath() {
        return appsPath;
    }

    public void setAppsPath(String appsPath) {
        logger.log(Level.INFO, "apps path = " + appsPath);
        this.appsPath = appsPath;
    }
}
