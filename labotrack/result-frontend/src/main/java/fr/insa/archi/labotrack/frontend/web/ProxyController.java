package fr.insa.archi.labotrack.frontend.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class ProxyController {

    private final RestClient sampleClient;
    private final RestClient analysisClient;

    public ProxyController(
            @Value("${SAMPLE_API_URL:http://sample-api:8081}") String sampleApiUrl,
            @Value("${ANALYSIS_API_URL:http://analysis-api:8082}") String analysisApiUrl) {
        this.sampleClient = RestClient.builder().baseUrl(sampleApiUrl).build();
        this.analysisClient = RestClient.builder().baseUrl(analysisApiUrl).build();
    }

    @PostMapping("/samples")
    public ResponseEntity<String> createSample(@RequestBody byte[] body) {
        String json = new String(body, StandardCharsets.UTF_8);
        return sampleClient.post()
                .uri("/samples")
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
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
