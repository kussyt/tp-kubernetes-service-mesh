package fr.insa.archi.labotrack.analysis.web;

import java.time.Instant;

public record AnalysisResult(
        Long sampleId,
        String patientName,
        String examType,
        String status,
        String biomarker,
        double value,
        String unit,
        String interpretation,
        Instant analyzedAt
) {
}
