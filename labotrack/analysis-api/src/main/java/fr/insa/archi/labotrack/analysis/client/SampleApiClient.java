package fr.insa.archi.labotrack.analysis.client;

import fr.insa.archi.labotrack.analysis.SampleApiProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SampleApiClient {

    private final RestClient restClient;

    public SampleApiClient(RestClient.Builder builder, SampleApiProperties properties) {
        this.restClient = builder.baseUrl(properties.baseUrl()).build();
    }

    public SampleDto getSample(Long id) {
        return restClient.get()
                .uri("/samples/{id}", id)
                .retrieve()
                .body(SampleDto.class);
    }
}
