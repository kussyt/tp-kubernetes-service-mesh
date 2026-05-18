package fr.insa.archi.labotrack.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BackendProperties.class)
public class ResultFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResultFrontendApplication.class, args);
    }
}
