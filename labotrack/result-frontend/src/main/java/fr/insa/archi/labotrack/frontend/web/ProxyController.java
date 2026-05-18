package fr.insa.archi.labotrack.frontend.web;

import fr.insa.archi.labotrack.frontend.BackendProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api")
public class ProxyController {

    private final RestClient sampleClient;
    private final RestClient analysisClient;

    public ProxyController(RestClient.Builder builder, BackendProperties properties) {
        this.sampleClient = builder.baseUrl(properties.sampleApiUrl()).build();
        this.analysisClient = builder.baseUrl(properties.analysisApiUrl()).build();
    }

    @PostMapping("/samples")
    public ResponseEntity<String> createSample(@RequestBody String body) {
        return sampleClient.post()
                .uri("/samples")
                .body(body)
                .retrieve()
                .toEntity(String.class);
    }

    @GetMapping("/samples/{id}")
    public ResponseEntity<String> getSample(@PathVariable Long id) {
        return sampleClient.get()
                .uri("/samples/{id}", id)
                .retrieve()
                .toEntity(String.class);
    }

    @PostMapping("/analyze/{id}")
    public ResponseEntity<String> analyze(@PathVariable Long id) {
        return analysisClient.post()
                .uri("/analyze/{id}", id)
                .retrieve()
                .toEntity(String.class);
    }
}
