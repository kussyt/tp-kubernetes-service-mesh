# TP Kubernetes, Minikube & Service Mesh (Linkerd) — LaboTrack

Projet **ARCHI** (INSA) : microservices Java sur **Kubernetes** (Minikube), conteneurisés avec **Docker**, sécurisés et observés via **Linkerd**.

**Énoncé** : [`docs/TP/tp_kubernetes (Services Mesh) 2026.pdf`](docs/TP/tp_kubernetes%20(Services%20Mesh)%202026.pdf)  
**Support cours** : [`docs/TP/document_tp_kubernetes.pdf`](docs/TP/document_tp_kubernetes.pdf)

## Équipe

| Nom | Prénom | Email |
|-----|--------|-------|
| FRANCK | Marius | marius.franck@uphf.fr |
| CARPENTIER | Simon | simon.carpentier@uphf.fr |

**Environnement** : Windows, Docker Desktop, Minikube (driver `docker`).

> **Remise** : dépôt Git public ou privé — inviter le compte GitHub `tondeur-h` (tondeur.herve@yahoo.fr), puis envoyer l’URL du dépôt par mail à tondeur.herve@yahoo.fr et herve.tondeur@uphf.fr (énoncé TP).

---

## Deux étapes du TP

| Étape | Contenu | Documentation |
|-------|---------|---------------|
| **1** | 20 questions Minikube + service démo Docker `/monservice/*` | [docs/etape1-kubernetes.md](docs/etape1-kubernetes.md) · [Réponses Q1–Q20](docs/etape1-reponses.md) |
| **2** | **LaboTrack** (3 microservices + Postgres + Linkerd) | [docs/etape2-labotrack.md](docs/etape2-labotrack.md) |

```text
tp-kubernetes-service-mesh/
├── README.md
├── docs/
│   ├── etape1-kubernetes.md      # Guide étape 1
│   ├── etape1-reponses.md        # Compte-rendu Q1–Q20
│   ├── etape2-labotrack.md       # Guide étape 2 + Linkerd
│   ├── captures/                 # Screenshots (etape1/, etape2/)
│   └── TP/                       # PDF officiels
├── demo-service/                 # Étape 1 — REST démo + Docker
├── labotrack/                    # Étape 2 — sample / analysis / frontend
├── k8s/                          # Manifests Kubernetes
└── scripts/                      # build.sh, push.sh, deploy.sh
```

---

## Étape 2 — LaboTrack (aperçu)

| Service | Port | API |
|---------|------|-----|
| `sample-api` | 8081 | `POST /samples`, `GET /samples/{id}` |
| `analysis-api` | 8082 | `POST /analyze/{id}` |
| `result-frontend` | 8080 | Interface HTML |

```text
result-frontend → analysis-api → sample-api → PostgreSQL
                      (Linkerd : mTLS, metrics, ServiceProfile, ServerAuthorization)
```

---

## Prérequis

- Docker 27+, Minikube, kubectl, Linkerd CLI
- JDK 21, Maven 3.9+

```bash
docker --version && minikube version && java -version && mvn -version
```

---

## Démarrage rapide (Étape 2 — quand le code sera prêt)

```bash
minikube start --driver=docker --cpus=4 --memory=6144
linkerd install --crds | kubectl apply -f -
linkerd install | kubectl apply -f -

./scripts/build.sh
eval $(minikube docker-env)   # PowerShell : minikube docker-env | Invoke-Expression
./scripts/push.sh
kubectl annotate namespace labotrack linkerd.io/inject=enabled --overwrite
./scripts/deploy.sh

minikube service result-frontend -n labotrack --url
```

---

## État d’avancement

- [x] Étape 1 — compte-rendu, captures, `demo-service`
- [x] LaboTrack — 3 microservices (Java 21 / Spring Boot)
- [x] Docker multi-stage + `labotrack/docker-compose.yml`
- [x] Manifests Kubernetes + Postgres
- [x] Scripts `build.sh`, `push.sh`, `deploy.sh`
- [x] Linkerd — ServiceProfile & ServerAuthorization (`k8s/linkerd/`)
- [x] Schéma architecture — [docs/architecture-labotrack.md](docs/architecture-labotrack.md)
- [ ] Déploiement Minikube testé de bout en bout
- [ ] Captures PNG étape 2 (`docs/captures/etape2/`)

## LaboTrack — démarrage local (Docker Compose)

```powershell
cd labotrack
docker compose up --build
```

Ouvrir [http://localhost:8080](http://localhost:8080)

## LaboTrack — Minikube

```powershell
$env:Path = "C:\Program Files\Kubernetes\Minikube;" + $env:Path
kubectl config use-context minikube

linkerd install --crds | kubectl apply -f -
linkerd install | kubectl apply -f -

# Git Bash ou WSL
./scripts/push.sh
./scripts/deploy.sh

minikube service result-frontend -n labotrack --url
```

---

## Liens utiles

- [Linkerd — Getting Started](https://linkerd.io/2.14/getting-started/)
- [Docker multi-stage builds](https://docs.docker.com/build/building/multi-stage/)
- [Minikube](https://minikube.sigs.k8s.io/docs/)
