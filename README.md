# gift-galaxy-microservices
## Description
Gift Galaxy using microservices.
## Tools
1. [Postman](https://www.postman.com/)
2. [Docker Desktop](https://www.docker.com/products/docker-desktop/)
3. [Helm](https://helm.sh/)
4. [Skaffold](https://skaffold.dev/)
5. [K9s](https://k9scli.io/)
## Local Development (Docker)
1. Clone the repository.
2. Find the microservices you need, go to the service directories and run the applications.
### Connect to the database
1. Open the terminal and run `docker-compose up -d`.
2. Open the database management tool and connect to the database.
#### Connect to MongoDB.
1. Using Database Management Tool to connect to the database.
   ```
   host: localhost:27017
   username: spring
   password: password
   ```
2. Open a console and create a database named `users`.
   ```
   use users
   db.createCollection("users")
   ```
#### Connect to PostgreSQL.
1. Using Database Management Tool to connect to the database.
   ```
   host: localhost:5432
   username: postgres
   password: password
   database: recommendations
   ```
## Kubernetes (Production and Local Development)
### 1. Setup CI/CD in GitHub repo
1. Set up the following secrets in the repository settings.
   ```
   DOCKERHUB_TOKEN
   DOCKERHUB_USERNAME
   DIGITALOCEAN_ACCESS_TOKEN
   ```
### 2. Install NGINX Ingress Controller
1. For local development
   ```
   helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
   helm repo update
   helm upgrade --install ingress-nginx ingress-nginx \
     --repo https://kubernetes.github.io/ingress-nginx \
     --namespace ingress-nginx --create-namespace
   ```
2. For production
   ```
   helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
   helm repo update
   kubectl create namespace ingress-nginx
   helm upgrade ingress-nginx ingress-nginx/ingress-nginx -f .k8s/values-ingress-nginx.yaml -n ingress-nginx --install
   ```
### 3. Create Secrets
1. Copy from example and setup secrets.
    ```
    cp ./k8s/recommendation-secret-example.yaml ./k8s/recommendation-secret.yaml
    cp ./k8s/user-secret-example.yaml ./k8s/user-secret.yaml
    ```
2. Create secrets.
   ```
   kubectl apply -f ./k8s/recommendation-secret.yaml
   kubectl apply -f ./k8s/user-secret.yaml
   ```
### 4. Add PVC (Production Only)
1. Add PVCs to the cluster.
   ```
   kubectl apply -f ./k8s/pvc-recommendation-postgres.yaml
   kubectl apply -f ./k8s/pvc-user-mysql.yaml
   ```
### 5. Start Helm Chart (Local Development Only)
1. Run the following command to start the application.
   ```
   make dev
   ```
### 6. Setup DNS
1. Add the following to your host file `sudo vim /etc/hosts`.
   ```
   127.0.0.1       user.giftgalaxy.dev
   127.0.0.1       recommendation.giftgalaxy.dev
   ```
### 7. Test connection
1. Try `http://user.giftgalaxy.dev/api/v1/ping` and `http://recommendation.giftgalaxy.dev/api/v1/ping` in your browser.