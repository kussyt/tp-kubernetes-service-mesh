# Scripts LaboTrack

| Script | Description |
|--------|-------------|
| `build.sh` | Construit les 3 images Docker (multi-stage Maven) |
| `push.sh` | Rebuild dans le daemon Docker de Minikube |
| `deploy.sh` | Applique les manifests K8s + policies Linkerd |

**Windows** : exécuter via **Git Bash** ou **WSL**.

```bash
chmod +x scripts/*.sh
./scripts/push.sh
./scripts/deploy.sh
```
