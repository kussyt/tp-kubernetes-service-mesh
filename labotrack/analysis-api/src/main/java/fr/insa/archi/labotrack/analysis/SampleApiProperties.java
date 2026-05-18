package fr.insa.archi.labotrack.analysis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "labotrack.sample-api")
public record SampleApiProperties(String baseUrl) {
}
