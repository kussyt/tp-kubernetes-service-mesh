# Captures d'écran — TP Kubernetes & Service Mesh

Les images (`*.png`, `*.jpg`) sont ignorées par Git (`.gitignore`) pour limiter la taille du dépôt.  
Ce fichier sert d’**index** ; les captures peuvent être regroupées en PDF pour la remise.

## Structure des dossiers

```text
docs/captures/
├── etape1/          # Q1–Q20 + Docker démo
└── etape2/          # LaboTrack + Linkerd
```

---

## Étape 1 — Partie A : Questions Minikube (Q1–Q20)

| Fichier | Question |
|---------|----------|
| `etape1/q01-minikube-docker.png` | (1) Minikube → Docker |
| `etape1/q02-addons-list.png` | (2) Addons installés |
| `etape1/q03-addon-enable.png` | (3) Addon activé |
| `etape1/q04-profiles.png` | (4) Profils + caractéristiques |
| `etape1/q05-profile-courant.png` | (5) Profil en cours |
| `etape1/q06-nouveau-profil.png` | (6) Nouveau profil |
| `etape1/q07-status.png` | (7) `minikube status` |
| `etape1/q08-dashboard-url.png` | (8) Accès dashboard |
| `etape1/q09-dashboard-vue.png` | (9) Vue dashboard |
| `etape1/q10-nodes.png` | (10) `kubectl get nodes` |
| `etape1/q11-node-add-delete.png` | (11) Ajout / suppression nœud |
| `etape1/q12-minikube-logs.png` | (12) Logs minikube |
| `etape1/q13-images.png` | (13) Images Docker Minikube |
| `etape1/q14-nginx-deploy.png` | (14) nginx deployment |
| `etape1/q15-nginx-service.png` | (15) Service nginx |
| `etape1/q16-describe.png` | (16) describe pod/svc |
| `etape1/q17-service-url.png` | (17) URL service |
| `etape1/q18-navigateur-nginx.png` | (18) nginx dans navigateur |
| `etape1/q19-exec-nginx.png` | (19) exec dans nginx |
| `etape1/q20-nginx-logs.png` | (20) logs nginx |

---

## Étape 1 — Partie B : Docker Build (demo-service)

| Fichier | Contenu |
|---------|---------|
| `etape1/demo-jar-local.png` | Test JAR en local (`java -jar`) |
| `etape1/demo-docker-compose.png` | `docker compose up` + curl |
| `etape1/demo-multistage-build.png` | Build `Dockerfile.multistage` |

---

## Étape 2 — LaboTrack & Linkerd

| Fichier | Contenu | Intégré dans le compte-rendu |
|---------|---------|------------------------------|
| `etape2/01-docker-compose-ps.png` | `docker compose ps` — stack locale | [etape2-labotrack.md](../etape2-labotrack.md) |
| `etape2/02-post-samples-api.png` | `POST /samples` (sample-api) | [etape2-labotrack.md](../etape2-labotrack.md) |
| `etape2/03-post-analyze-api.png` | `POST /analyze/{id}` (analysis-api) | [etape2-labotrack.md](../etape2-labotrack.md) |

---

## Conseils

- Terminal lisible (police ≥ 12 pt), une preuve par capture
- Masquer données personnelles si besoin
- Export PDF possible pour remise mail (voir énoncé TP)
