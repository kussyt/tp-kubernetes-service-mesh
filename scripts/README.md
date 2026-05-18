# Scripts d'automatisation

Scripts shell pour le cycle **build → images Minikube → déploiement K8s**.

| Script | Rôle |
|--------|------|
| `build.sh` | Build Maven + `docker build` des 3 services |
| `push.sh` | Charge les images dans le daemon Docker de Minikube |
| `deploy.sh` | `kubectl apply -f k8s/` et attente des Pods Ready |

## Usage (Linux / macOS / Git Bash)

```bash
chmod +x scripts/*.sh
./scripts/build.sh
eval $(minikube docker-env)
./scripts/push.sh
./scripts/deploy.sh
```

## Windows (PowerShell)

Exécuter les commandes équivalentes manuellement ou via **Git Bash** / **WSL**.

```powershell
minikube docker-env | Invoke-Expression
```

*Les scripts seront générés à l'étape suivante du TP.*
