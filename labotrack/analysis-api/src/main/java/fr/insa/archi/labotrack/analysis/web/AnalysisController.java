package fr.insa.archi.labotrack.analysis.web;

import fr.insa.archi.labotrack.analysis.service.AnalysisService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analyze")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/{id}")
    public AnalysisResult analyze(@PathVariable Long id) throws InterruptedException {
        return analysisService.analyze(id);
    }
}
