FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copiar Maven Wrapper
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# Dar permisos de ejecución a mvnw
RUN chmod +x mvnw

# Descargar dependencias
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY src src

# Compilar aplicación
RUN ./mvnw clean install -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar JAR desde build
COPY --from=build /app/target/v1-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE $PORT

# Ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
