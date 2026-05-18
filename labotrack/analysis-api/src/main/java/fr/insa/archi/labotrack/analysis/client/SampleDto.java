package fr.insa.archi.labotrack.analysis.client;

import java.time.Instant;

public record SampleDto(
        Long id,
        String patientName,
        String examType,
        String sampleType,
        String status,
        Instant createdAt
) {
}
