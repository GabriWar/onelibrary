Local MariaDB for Library app

Start MariaDB locally with Docker Compose:

```fish
docker compose up -d
```

This creates a database `librarydb` with user `libraryuser` / `librarypass`.

Application configuration (in `src/main/resources/application.properties`):

```
spring.datasource.url=jdbc:mariadb://localhost:3306/librarydb
spring.datasource.username=libraryuser
spring.datasource.password=librarypass
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Run the application:

```fish
./mvnw -DskipTests spring-boot:run
```

Then access endpoints (e.g. existing `/greeting` or new `/random`).
