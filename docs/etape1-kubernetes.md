# Étape 1 — Manipulations Kubernetes, Minikube & Docker Build

> **Énoncé officiel** : `docs/TP/tp_kubernetes (Services Mesh) 2026.pdf` (pages 1–2)  
> **Support cours** : `docs/TP/document_tp_kubernetes.pdf` (installation, pods, services, namespaces…)

Cette étape comporte **deux parties distinctes** à rendre :

| Partie | Contenu | Document de rendu |
|--------|---------|-------------------|
| **A** | 20 questions Minikube / kubectl | [etape1-reponses.md](etape1-reponses.md) |
| **B** | Service REST démo + 3 variantes Docker | dossier [`demo-service/`](../demo-service/) |

> **LaboTrack** (3 microservices) = **Étape 2** uniquement. Ne pas le confondre avec le service démo `/monservice/*`.

---

## Partie A — Questions Kubernetes (Q1 à Q20)

Pour chaque question : **commande(s)** + **capture d’écran** (CLI ou navigateur).

Le gabarit de réponses est dans **[etape1-reponses.md](etape1-reponses.md)**.  
Les captures vont dans `docs/captures/etape1/` (voir index).

### Prérequis installation

Suivre `docs/TP/document_tp_kubernetes.pdf` :

- Docker 27+, Minikube, kubectl
- OpenJDK 21, Maven 3.9+
- Linux recommandé (Ubuntu 24.04) ; Windows possible avec adaptations PowerShell

```bash
minikube start --driver=docker --cpus=4 --memory=6144
```

**PowerShell (Windows)** — activer le daemon Docker de Minikube :

```powershell
minikube docker-env | Invoke-Expression
```

### Arrêt du cluster (Q20 suite)

```bash
minikube stop
```

---

## Partie B — Service REST démo & Docker Build

### Objectif

Créer un microservice Spring Boot **simple** (démonstrateur, pas LaboTrack) :

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/monservice/echo/{nom}` | Retourne un message avec `{nom}` |
| POST | `/monservice/hello` | Body JSON `{"nom":"..."}` → réponse de salutation |

Code source : [`demo-service/`](../demo-service/).

### Cas 1 — Fat-JAR (compilation locale)

```bash
cd demo-service
mvn clean package -DskipTests
java -jar target/demo-service-*.jar
# Tester : curl http://localhost:8080/monservice/echo/test
```

Puis image Docker à partir du JAR déjà compilé : `Dockerfile` (simple).

### Cas 2 — Docker Compose (image simple)

```bash
cd demo-service
docker compose up --build
curl http://localhost:8080/monservice/echo/world
```

Fichier : `docker-compose.yml`

### Cas 3 — Build multi-stage (sans `mvn` local)

Compilation **dans** le Dockerfile, en une seule commande :

```bash
cd demo-service
docker build -f Dockerfile.multistage -t demo-service:multistage .
docker run --rm -p 8080:8080 demo-service:multistage
```

**Intérêt du multi-stage** : image finale légère (runtime JRE seul), pas d’outils Maven dans l’image de prod, reproductibilité CI/CD, séparation build / exécution.

| Fichier | Rôle |
|---------|------|
| `Dockerfile` | Fat-JAR pré-compilé en local |
| `Dockerfile.multistage` | Build Maven + runtime en une passe |
| `docker-compose.yml` | Orchestration locale du Cas 1/2 |

### Livrables Partie B

- [ ] Tests locaux du JAR (capture)
- [ ] `docker compose` fonctionnel (capture)
- [ ] Build multi-stage réussi (capture)
- [ ] Explication écrite de l’intérêt du multi-stage (dans README demo-service)

---

## Checklist Étape 1 (complète)

### Partie A — Kubernetes
- [ ] Q1–Q12 : Minikube (profils, addons, dashboard, nœuds, logs…)
- [ ] Q13–Q20 : nginx impératif, service, URL, navigateur, exec, logs
- [ ] `minikube stop` documenté

### Partie B — Docker
- [ ] Service `/monservice/*` testé en local (JAR)
- [ ] Dockerfile simple + docker-compose
- [ ] Dockerfile multistage sans compilation locale

---

## Suite du TP

→ [Étape 2 — LaboTrack & Linkerd](etape2-labotrack.md)
