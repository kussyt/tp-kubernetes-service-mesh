# demo-service — Étape 1 (Docker Build)

Service REST **démonstrateur** demandé par l’énoncé TP (distinct de LaboTrack).

## API

| Méthode | Endpoint | Exemple |
|---------|----------|---------|
| GET | `/monservice/echo/{nom}` | `curl http://localhost:8080/monservice/echo/Alice` |
| POST | `/monservice/hello` | `curl -X POST http://localhost:8080/monservice/hello -H "Content-Type: application/json" -d '{"nom":"Bob"}'` |

## Cas 1 — Fat-JAR local

```bash
mvn clean package -DskipTests
java -jar target/demo-service-1.0.0.jar
```

Le plugin `spring-boot-maven-plugin` produit un **über-JAR** (toutes dépendances incluses).

## Cas 2 — Dockerfile simple + Compose

```bash
mvn clean package -DskipTests
docker compose up --build
curl http://localhost:8080/monservice/echo/test
```

Fichiers : `Dockerfile`, `docker-compose.yml`

## Cas 3 — Multi-stage (sans mvn local)

```bash
docker build -f Dockerfile.multistage -t demo-service:multistage .
docker run --rm -p 8080:8080 demo-service:multistage
```

### Intérêt du multi-stage

| | Image build (Maven) | Image finale (JRE) |
|--|---------------------|---------------------|
| Taille | ~400 Mo+ | ~80 Mo |
| Contenu | JDK, Maven, sources | JAR + JRE seulement |
| Sécurité | Surface d’attaque large | Minimal |

Reproductibilité : même Dockerfile en CI/CD, pas de dépendance à l’environnement développeur.

## Fichiers

| Fichier | Usage |
|---------|--------|
| `Dockerfile` | JAR pré-compilé |
| `Dockerfile.multistage` | Build + run en une commande |
| `docker-compose.yml` | Test local orchestré |
