package fr.insa.archi.labotrack.sample.web;

import fr.insa.archi.labotrack.sample.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/samples")
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping
    public SampleResponse create(@RequestBody CreateSampleRequest request) {
        return SampleResponse.from(sampleService.create(request));
    }

    @GetMapping("/{id}")
    public SampleResponse getById(@PathVariable Long id) {
        return SampleResponse.from(sampleService.findById(id));
    }
}
