# ─── Stage 1: Build ───────────────────────────────────────────────────────────
FROM eclipse-temurin:26-jdk AS builder

WORKDIR /build

COPY agrotrack/.mvn/ .mvn/
COPY agrotrack/mvnw agrotrack/pom.xml ./

RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

COPY agrotrack/src ./src

RUN ./mvnw package -DskipTests -q

# ─── Stage 2: Runtime ─────────────────────────────────────────────────────────
FROM eclipse-temurin:26-jre

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
