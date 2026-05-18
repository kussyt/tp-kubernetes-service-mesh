#!/usr/bin/env bash
set -euo pipefail

# Charge les images dans le daemon Docker de Minikube (pas de registry externe).
echo "=== Configuration du daemon Docker Minikube ==="
eval "$(minikube docker-env)"

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
VERSION="${1:-1.0.0}"

"${ROOT}/scripts/build.sh" "${VERSION}"

echo "=== Images disponibles dans Minikube ==="
docker images | grep labotrack || true
