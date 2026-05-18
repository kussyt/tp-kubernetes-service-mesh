package fr.insa.archi.labotrack.sample.service;

import fr.insa.archi.labotrack.sample.domain.Sample;
import fr.insa.archi.labotrack.sample.repository.SampleRepository;
import fr.insa.archi.labotrack.sample.web.CreateSampleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SampleService {

    private final SampleRepository repository;

    public SampleService(SampleRepository repository) {
        this.repository = repository;
    }

    public Sample create(CreateSampleRequest request) {
        validate(request.patientName(), "patientName");
        validate(request.examType(), "examType");
        validate(request.sampleType(), "sampleType");
        return repository.save(new Sample(request.patientName(), request.examType(), request.sampleType()));
    }

    public Sample findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Échantillon introuvable : " + id));
    }

    private void validate(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, field + " est obligatoire");
        }
    }
}
