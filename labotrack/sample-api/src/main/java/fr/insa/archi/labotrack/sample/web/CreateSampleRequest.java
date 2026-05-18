package fr.insa.archi.labotrack.sample.web;

public record CreateSampleRequest(
        String patientName,
        String examType,
        String sampleType
) {
}
