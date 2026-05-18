# result-frontend

Application **Spring Boot** servant une interface **HTML/CSS** simple pour démontrer le parcours utilisateur LaboTrack.

## Responsabilités

- Page unique permettant de :
  1. **Créer** un échantillon (appel `POST` vers sample-api)
  2. **Lancer** une analyse (appel `POST` vers analysis-api)
  3. **Afficher** le résultat de l'analyse

## Stack

- Java 21 · Spring Boot 3 (Web MVC)
- Fichiers statiques : `src/main/resources/static/` (`index.html`, `style.css`, `app.js`)
- Port par défaut : **8080**

## Configuration

| Variable | Description | Défaut (local) |
|----------|-------------|----------------|
| `SAMPLE_API_URL` | API samples | `http://localhost:8081` |
| `ANALYSIS_API_URL` | API analyses | `http://localhost:8082` |
| `SERVER_PORT` | Port HTTP | `8080` |

> En navigateur, les appels passent par un **proxy côté serveur** (contrôleur Spring) pour éviter les problèmes CORS et utiliser les URLs internes en cluster.

## Lancer en local

```bash
# Démarrer sample-api (8081) et analysis-api (8082)
mvn spring-boot:run
```

Ouvrir : [http://localhost:8080](http://localhost:8080)

## Build & Docker

```bash
mvn clean package -DskipTests
docker build -t labotrack/result-frontend:1.0.0 .
```

## Kubernetes

- Service exposé : **NodePort** ou `minikube service`
- Manifest : `k8s/result-frontend.yaml`

## Structure des assets (prévue)

```text
src/main/resources/static/
├── index.html
├── style.css
└── app.js
```
