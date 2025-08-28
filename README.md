# rest-service-complete

Spring Boot REST service (library demo). Local build:

```bash
# run (Linux / fish)
./mvnw -Dspring-boot.run.arguments="--app.seed.bulk=true" spring-boot:run
```

Database: by default configured for MariaDB (see `docker-compose.yml`). Use `application-local.properties` for local overrides (ignored).
