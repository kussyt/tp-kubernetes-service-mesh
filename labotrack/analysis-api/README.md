# analysis-api

Microservice **Spring Boot** : lance une analyse « biologique » simulée pour un échantillon existant.

## Responsabilités

- Déclencher une analyse : `POST /analyze/{id}`
- Appeler **sample-api** pour récupérer l'échantillon
- Retourner un résultat fictif (statut, score, interprétation)

## Stack

- Java 21 · Spring Boot 3 · WebClient / RestTemplate
- Port par défaut : **8082**

## Flux

```text
Client  →  POST /analyze/{id}  →  analysis-api
                                      │
                                      ▼
                              GET sample-api/samples/{id}
                                      │
                                      ▼
                              Résultat simulé (JSON)
```

## Configuration

| Variable | Description | Défaut (local) |
|----------|-------------|----------------|
| `SAMPLE_API_URL` | URL de sample-api | `http://localhost:8081` |
| `SERVER_PORT` | Port HTTP | `8082` |

## Lancer en local

```bash
# sample-api doit être démarré sur le port 8081
mvn spring-boot:run
```

```bash
curl -X POST http://localhost:8082/analyze/1
```

Exemple de réponse :

```json
{
  "sampleId": 1,
  "status": "COMPLETED",
  "biomarker": "Simulated-Marker-X",
  "score": 0.87,
  "interpretation": "Valeur dans la norme (simulation pédagogique)"
}
```

## Build & Docker

```bash
mvn clean package -DskipTests
docker build -t labotrack/analysis-api:1.0.0 .
```

## Kubernetes

- Service interne : `http://analysis-api:8082`
- Variable : `SAMPLE_API_URL=http://sample-api:8081`

Manifest : `k8s/analysis-api.yaml`
