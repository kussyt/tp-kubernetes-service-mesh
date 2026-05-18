#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
VERSION="${1:-1.0.0}"

echo "=== Build images LaboTrack ${VERSION} ==="

docker build -t "labotrack/sample-api:${VERSION}" "${ROOT}/labotrack/sample-api"
docker build -t "labotrack/analysis-api:${VERSION}" "${ROOT}/labotrack/analysis-api"
docker build -t "labotrack/result-frontend:${VERSION}" "${ROOT}/labotrack/result-frontend"

echo "=== Images construites ==="
docker images | grep labotrack || true
