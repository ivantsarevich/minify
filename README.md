# Minify

Minify is a simple application that provides a REST API for working with files in the MinIO object storage. The
application allows users to upload files, download them by ID, and manage data in the storage.

## Usage

1. Clone the project to your computer

```
git clone https://github.com/ivantsarevich/minify.git
```

2. Rename .env.example to .env
3. Build the application

```
gradlew build -x test
```

4. Create a Docker image

```
docker build . -t minify
```

5. Create a shared Docker network

```
docker network create shared-network
```

6. Start MinIO

```
docker compose -f minio.yaml up
```

7. Update .env with your MinIO credentials
8. Start the application

```
docker compose up
```

## Stack

- Java
- Spring Boot Web
- Spring Boot Data JPA
- Postgres
- Flyway
- Lombok
- Swagger
- Docker