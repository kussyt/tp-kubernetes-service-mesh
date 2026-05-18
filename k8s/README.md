# Manifests Kubernetes — LaboTrack

| Fichier | Ressource |
|---------|-----------|
| `namespace.yaml` | Namespace `labotrack` |
| `postgres.yaml` | Secret + Deployment + Service PostgreSQL |
| `sample-api.yaml` | API échantillons (8081) |
| `analysis-api.yaml` | API analyses (8082) |
| `result-frontend.yaml` | Frontend NodePort 30080 |
| `linkerd/` | ServiceProfile, ServerAuthorization |

## Déploiement

```bash
./scripts/push.sh    # build dans le daemon Minikube
./scripts/deploy.sh
```

## Accès frontend

```bash
minikube service result-frontend -n labotrack --url
# ou http://$(minikube ip):30080
```
