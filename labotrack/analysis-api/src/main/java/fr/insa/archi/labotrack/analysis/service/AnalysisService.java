package fr.insa.archi.labotrack.analysis.service;

import fr.insa.archi.labotrack.analysis.client.SampleApiClient;
import fr.insa.archi.labotrack.analysis.client.SampleDto;
import fr.insa.archi.labotrack.analysis.web.AnalysisResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnalysisService {

    private static final Map<String, String[]> EXAM_BIOMARKERS = Map.of(
            "glycemie", new String[]{"Glucose", "g/L"},
            "blood", new String[]{"Hémoglobine", "g/dL"},
            "serum", new String[]{"CRP", "mg/L"}
    );

    private final SampleApiClient sampleApiClient;
    private final long artificialLatencyMs;

    public AnalysisService(
            SampleApiClient sampleApiClient,
            @Value("${labotrack.analysis.latency-ms:300}") long artificialLatencyMs) {
        this.sampleApiClient = sampleApiClient;
        this.artificialLatencyMs = artificialLatencyMs;
    }

    public AnalysisResult analyze(Long sampleId) throws InterruptedException {
        Thread.sleep(artificialLatencyMs);

        SampleDto sample = sampleApiClient.getSample(sampleId);
        String examKey = sample.examType() == null ? "blood" : sample.examType().toLowerCase();
        String[] biomarker = EXAM_BIOMARKERS.getOrDefault(examKey, new String[]{"Marqueur-X", "UI"});

        double value = Math.round(ThreadLocalRandom.current().nextDouble(0.5, 1.5) * 100.0) / 100.0;
        String interpretation = value < 1.0
                ? "Valeur dans la norme (simulation pédagogique)"
                : "Valeur légèrement élevée (simulation pédagogique)";

        return new AnalysisResult(
                sample.id(),
                sample.patientName(),
                sample.examType(),
                "COMPLETED",
                biomarker[0],
                value,
                biomarker[1],
                interpretation,
                Instant.now()
        );
    }
}
