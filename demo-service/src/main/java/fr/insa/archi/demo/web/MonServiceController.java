package fr.insa.archi.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/monservice")
public class MonServiceController {

    @GetMapping("/echo/{nom}")
    public Map<String, String> echo(@PathVariable String nom) {
        return Map.of(
                "message", "Echo : " + nom,
                "nom", nom
        );
    }

    @PostMapping("/hello")
    public Map<String, String> hello(@RequestBody HelloRequest request) {
        String nom = request.nom() != null ? request.nom() : "inconnu";
        return Map.of(
                "message", "Bonjour " + nom,
                "nom", nom
        );
    }
}
