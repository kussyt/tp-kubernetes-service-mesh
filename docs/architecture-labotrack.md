# Architecture LaboTrack

## Vue d'ensemble

```text
                    ┌─────────────────────────────────────┐
                    │         Namespace labotrack          │
                    │         (+ Linkerd sidecar)          │
                    │                                      │
  Navigateur ──────►│  result-frontend :8080 (NodePort)   │
                    │         │              │             │
                    │         │ REST         │ REST        │
                    │         ▼              ▼             │
                    │  analysis-api:8082  sample-api:8081  │
                    │         │              │             │
                    │         └──────►───────┘             │
                    │                        │             │
                    │                        ▼             │
                    │                   postgres:5432      │
                    └─────────────────────────────────────┘
```

## Microservices

| Service | Rôle | Stockage |
|---------|------|----------|
| sample-api | Enregistrement des prélèvements | PostgreSQL |
| analysis-api | Analyse simulée (latence 300 ms) | Stateless |
| result-frontend | Interface web + proxy API | Stateless |

## Flux métier

1. L'utilisateur crée un échantillon via le frontend → `POST /api/samples` → sample-api → PostgreSQL.
2. Il lance une analyse → `POST /api/analyze/{id}` → analysis-api → `GET sample-api/samples/{id}` → résultat fictif.
3. Linkerd observe le trafic inter-pods (mTLS, métriques, ServiceProfile).

## Équipe

Marius FRANCK, Simon CARPENTIER — UPHF / ARCHI 2026.
