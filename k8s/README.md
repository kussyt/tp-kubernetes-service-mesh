# Manifests Kubernetes — LaboTrack

Manifests YAML pour le namespace **`labotrack`**.

## Fichiers prévus

| Fichier | Contenu |
|---------|---------|
| `namespace.yaml` | Namespace `labotrack` |
| `postgres.yaml` | PostgreSQL + Service + Secret |
| `sample-api.yaml` | Deployment + Service (8081) |
| `analysis-api.yaml` | Deployment + Service (8082) |
| `result-frontend.yaml` | Deployment + Service NodePort (8080) |

## Déploiement

```bash
kubectl apply -f k8s/
kubectl -n labotrack get all
```

Ou via le script : `../scripts/deploy.sh`

## Notes pédagogiques

- `imagePullPolicy: Never` pour les images construites dans Minikube
- Les Services utilisent des noms DNS courts (`sample-api`, `postgres`)
- Linkerd : annoter le namespace `linkerd.io/inject=enabled`

*Les fichiers YAML seront générés à l'étape suivante du TP.*
