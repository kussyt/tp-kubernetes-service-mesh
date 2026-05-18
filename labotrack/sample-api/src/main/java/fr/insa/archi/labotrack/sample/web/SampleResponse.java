package fr.insa.archi.labotrack.sample.web;

import fr.insa.archi.labotrack.sample.domain.Sample;
import fr.insa.archi.labotrack.sample.domain.SampleStatus;

import java.time.Instant;

public record SampleResponse(
        Long id,
        String patientName,
        String examType,
        String sampleType,
        SampleStatus status,
        Instant createdAt
) {
    public static SampleResponse from(Sample sample) {
        return new SampleResponse(
                sample.getId(),
                sample.getPatientName(),
                sample.getExamType(),
                sample.getSampleType(),
                sample.getStatus(),
                sample.getCreatedAt()
        );
    }
}
