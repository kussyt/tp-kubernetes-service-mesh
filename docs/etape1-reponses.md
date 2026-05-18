# Étape 1 — Réponses aux questions (Q1 à Q20)

> **Consigne TP** : pour chaque question, indiquer la **commande** utilisée et joindre une **capture d’écran**  
> (`docs/captures/etape1/qXX-*.png`).

**Équipe** : Marius FRANCK (marius.franck@uphf.fr), Simon CARPENTIER (simon.carpentier@uphf.fr)

**Environnement** : Windows 10/11, Docker Desktop, Minikube (driver `docker`)

---

## Gestion de Minikube

### (1) Vérifiez que minikube pointe correctement vers le moteur Docker ?

**Commande(s)** :

```bash
minikube config get driver
docker version
minikube start --driver=docker   # si pas encore démarré
```

**Réponse** : _[Ex. : le driver est `docker`, `docker version` répond, `minikube status` affiche Running]_

**Capture** : `docs/captures/etape1/q01-minikube-docker.png`

---

### (2) Quels sont les addons actuellement installés ?

**Commande(s)** :

```bash
minikube addons list
```

**Réponse** : _[Lister les addons ENABLED, ex. storage-provisioner, default-storageclass…]_

**Capture** : `docs/captures/etape1/q02-addons-list.png`

---

### (3) Installez celle qui vous semble intéressante, pourquoi ?

**Commande(s)** :

```bash
minikube addons enable metrics-server
# ou : minikube addons enable dashboard
```

**Réponse** : _[Ex. : `metrics-server` pour les métriques CPU/RAM des pods ; utile avant Linkerd/Prometheus]_

**Capture** : `docs/captures/etape1/q03-addon-enable.png`

---

### (4) Lister les profils actifs sous minikube avec toutes leurs caractéristiques ?

**Commande(s)** :

```bash
minikube profile list -o table
# ou
minikube profile list
```

**Réponse** : _[Décrire profil, driver, IP, status…]_

**Capture** : `docs/captures/etape1/q04-profiles.png`

---

### (5) Quels sont les profils en cours ?

**Commande(s)** :

```bash
minikube profile list
kubectl config current-context
```

**Réponse** : _[Profil actif, ex. `minikube`]_

**Capture** : `docs/captures/etape1/q05-profile-courant.png`

---

### (6) Comment créer un nouveau profil ? Que représente un profil ?

**Commande(s)** :

```bash
minikube start -p dev-cluster --driver=docker
minikube profile list
```

**Réponse** : _Un profil = un cluster Minikube isolé (config, nœuds, addons, contexte kubectl séparé). Permet plusieurs environnements sur la même machine._

**Capture** : `docs/captures/etape1/q06-nouveau-profil.png`

---

### (7) Afficher le statut de minikube ?

**Commande(s)** :

```bash
minikube status
```

**Réponse** : _[host, kubelet, apiserver : Running]_

**Capture** : `docs/captures/etape1/q07-status.png`

---

### (8) Comment accéder au dashboard de minikube ?

**Commande(s)** :

```bash
minikube addons enable dashboard
minikube dashboard
# URL typique : http://127.0.0.1:xxxxx/api/v1/namespaces/kubernetes-dashboard/services/...
```

**Capture** : `docs/captures/etape1/q08-dashboard-url.png`

---

### (9) Qu’est-ce que le Dashboard ? Que présente-t-il ?

**Réponse** (sans commande obligatoire) : _Interface web pour visualiser pods, deployments, services, namespaces, logs, ressources du cluster. Complément à `kubectl` et Lens._

**Capture** : `docs/captures/etape1/q09-dashboard-vue.png`

---

### (10) Lister les nœuds d’un profil ?

**Commande(s)** :

```bash
kubectl get nodes -o wide
```

**Réponse** : _[Nom du nœud minikube, STATUS Ready, VERSION…]_

**Capture** : `docs/captures/etape1/q10-nodes.png`

---

### (11) Ajouter un nœud à un profil minikube, puis supprimer ce nœud

**Commande(s)** :

```bash
minikube node add -p minikube
kubectl get nodes
minikube node delete minikube-m02 -p minikube   # adapter le nom affiché
kubectl get nodes
```

**Réponse** : _[Décrire l’ajout puis la suppression ; nécessite suffisamment de RAM]_

**Capture** : `docs/captures/etape1/q11-node-add-delete.png`

---

### (12) Consulter les logs de minikube — comment faire ?

**Commande(s)** :

```bash
minikube logs
# ou logs d'un composant :
minikube logs --file=logs.txt
```

**Réponse** : _[Où sont les logs, à quoi servent-ils pour le debug]_

**Capture** : `docs/captures/etape1/q12-minikube-logs.png`

---

## Gestion des pods et services

### (13) Lister les images actuellement en exécution dans Minikube

**Commande(s)** :

```bash
eval $(minikube docker-env)          # Linux/macOS
# minikube docker-env | Invoke-Expression   # PowerShell
docker ps
docker images
```

**Réponse** : _[Lister conteneurs/images du daemon Minikube]_

**Capture** : `docs/captures/etape1/q13-images.png`

---

### (14) Lancer nginx dans un pod et/ou deployment (mode impératif)

**Commande(s)** :

```bash
kubectl create deployment nginx-tp --image=nginx:alpine --replicas=1
# ou pod seul :
kubectl run nginx-pod-tp --image=nginx:alpine --port=80
kubectl get pods,deploy
```

**Capture** : `docs/captures/etape1/q14-nginx-deploy.png`

---

### (15) Créer un service (mode impératif) pour accéder à nginx

**Commande(s)** :

```bash
kubectl expose deployment nginx-tp --type=NodePort --port=80 --target-port=80
kubectl get svc
```

**Capture** : `docs/captures/etape1/q15-nginx-service.png`

---

### (16) Visualiser les informations du pod et du service

**Commande(s)** :

```bash
kubectl describe pod -l app=nginx-tp
kubectl describe svc nginx-tp
```

**Capture** : `docs/captures/etape1/q16-describe.png`

---

### (17) Obtenir l’URL du service

**Commande(s)** :

```bash
minikube service nginx-tp --url
# ou
kubectl get svc nginx-tp
```

**Capture** : `docs/captures/etape1/q17-service-url.png`

---

### (18) Exécuter le service dans un navigateur

**Réponse** : _Ouvrir l’URL obtenue en (17) dans Firefox/Chrome ; page d’accueil nginx visible._

**Capture** : `docs/captures/etape1/q18-navigateur-nginx.png`

---

### (19) Lancer une commande bash dans le conteneur nginx

**Commande(s)** :

```bash
kubectl get pods
kubectl exec -it <nom-pod-nginx> -- /bin/sh
# dans le conteneur : cat /etc/nginx/nginx.conf
```

**Capture** : `docs/captures/etape1/q19-exec-nginx.png`

---

### (20) Lister les logs du conteneur nginx

**Commande(s)** :

```bash
kubectl logs <nom-pod-nginx>
kubectl logs -f deployment/nginx-tp
```

**Capture** : `docs/captures/etape1/q20-nginx-logs.png`

---

## Arrêt Minikube

```bash
minikube stop
```

**Capture** (optionnelle) : `docs/captures/etape1/q21-minikube-stop.png`

---

## Nettoyage ressources nginx TP

```bash
kubectl delete deployment nginx-tp
kubectl delete service nginx-tp
kubectl delete pod nginx-pod-tp   # si créé en pod seul
```
