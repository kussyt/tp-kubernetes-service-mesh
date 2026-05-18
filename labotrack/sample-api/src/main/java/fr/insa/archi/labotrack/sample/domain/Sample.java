package fr.insa.archi.labotrack.sample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private String examType;

    @Column(nullable = false)
    private String sampleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SampleStatus status = SampleStatus.RECEIVED;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    protected Sample() {
    }

    public Sample(String patientName, String examType, String sampleType) {
        this.patientName = patientName;
        this.examType = examType;
        this.sampleType = sampleType;
    }

    public Long getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getExamType() {
        return examType;
    }

    public String getSampleType() {
        return sampleType;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public void setStatus(SampleStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
