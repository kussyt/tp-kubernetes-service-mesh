# LaboTrack — Microservices

Dossier racine des trois microservices du laboratoire virtuel **LaboTrack**.

## Services

| Dossier | Port | Description |
|---------|------|-------------|
| [sample-api](sample-api/) | 8081 | CRUD samples + PostgreSQL |
| [analysis-api](analysis-api/) | 8082 | Analyse simulée + appel sample-api |
| [result-frontend](result-frontend/) | 8080 | Interface HTML |

## Ordre de démarrage (local)

1. PostgreSQL
2. `sample-api`
3. `analysis-api`
4. `result-frontend`

## Build global

```bash
for dir in sample-api analysis-api result-frontend; do
  (cd "$dir" && mvn clean package -DskipTests)
done
```

## Technologies communes

- Java 21
- Spring Boot 3.3.x
- Maven (parent POM à venir)

Voir le [README principal](../README.md) et la [documentation](../docs/).
