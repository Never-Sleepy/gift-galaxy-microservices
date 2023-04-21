# gift-galaxy-microservices
## Description
Gift Galaxy using microservices.
## Tools
1. [Postman](https://www.postman.com/)
2. [Docker Desktop](https://www.docker.com/products/docker-desktop/)
## Development
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