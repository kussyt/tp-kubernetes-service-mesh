#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

echo "=== Déploiement namespace labotrack ==="
kubectl apply -f "${ROOT}/k8s/namespace.yaml"

echo "=== Linkerd : injection automatique sur le namespace ==="
kubectl annotate namespace labotrack linkerd.io/inject=enabled --overwrite

echo "=== Ressources applicatives ==="
kubectl apply -f "${ROOT}/k8s/postgres.yaml"
kubectl apply -f "${ROOT}/k8s/sample-api.yaml"
kubectl apply -f "${ROOT}/k8s/analysis-api.yaml"
kubectl apply -f "${ROOT}/k8s/result-frontend.yaml"

if kubectl get crd serviceprofiles.linkerd.io >/dev/null 2>&1; then
  echo "=== Policies Linkerd ==="
  kubectl apply -f "${ROOT}/k8s/linkerd/"
fi

echo "=== Attente des pods ==="
kubectl -n labotrack wait --for=condition=ready pod -l app=postgres --timeout=120s
kubectl -n labotrack wait --for=condition=ready pod -l app=sample-api --timeout=180s
kubectl -n labotrack wait --for=condition=ready pod -l app=analysis-api --timeout=180s
kubectl -n labotrack wait --for=condition=ready pod -l app=result-frontend --timeout=180s

echo "=== État du cluster ==="
kubectl -n labotrack get pods,svc

echo ""
echo "Frontend : minikube service result-frontend -n labotrack --url"
echo "Ou       : http://$(minikube ip):30080"
