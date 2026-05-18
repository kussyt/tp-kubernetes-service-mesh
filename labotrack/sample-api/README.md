# sample-api

Microservice **Spring Boot** : gestion des prélèvements biologiques (samples) avec persistance **PostgreSQL**.

## Responsabilités

- Créer un échantillon : `POST /samples`
- Consulter un échantillon : `GET /samples/{id}`

## Stack

- Java 21 · Spring Boot 3 · Spring Data JPA
- PostgreSQL
- Port par défaut : **8081**

## Modèle de données (exemple)

```json
{
  "id": 1,
  "label": "Patient-001",
  "type": "blood",
  "createdAt": "2026-05-18T10:00:00Z"
}
```

## Configuration

| Variable | Description | Défaut (local) |
|----------|-------------|----------------|
| `SPRING_DATASOURCE_URL` | JDBC PostgreSQL | `jdbc:postgresql://localhost:5432/labotrack` |
| `SPRING_DATASOURCE_USERNAME` | Utilisateur DB | `labotrack` |
| `SPRING_DATASOURCE_PASSWORD` | Mot de passe DB | `labotrack` |
| `SERVER_PORT` | Port HTTP | `8081` |

## Lancer en local

```bash
# PostgreSQL doit être démarré (docker compose ou local)
mvn spring-boot:run
```

```bash
curl -X POST http://localhost:8081/samples \
  -H "Content-Type: application/json" \
  -d '{"label":"Test-A","type":"blood"}'

curl http://localhost:8081/samples/1
```

## Build & Docker

```bash
mvn clean package -DskipTests
docker build -t labotrack/sample-api:1.0.0 .
```

## Kubernetes

Service interne : `http://sample-api:8081` (namespace `labotrack`).

Manifest : `k8s/sample-api.yaml` (à générer à l'étape suivante).
