package fr.insa.archi.labotrack.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SampleApiProperties.class)
public class AnalysisApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalysisApiApplication.class, args);
    }
}
