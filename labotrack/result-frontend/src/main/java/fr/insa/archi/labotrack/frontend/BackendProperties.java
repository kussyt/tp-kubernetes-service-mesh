package fr.insa.archi.labotrack.frontend;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "labotrack.backend")
public record BackendProperties(String sampleApiUrl, String analysisApiUrl) {
}
