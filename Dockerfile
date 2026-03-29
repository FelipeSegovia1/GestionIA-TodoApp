# Etapa 1: Compilación (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Copiamos el pom.xml y el código fuente
COPY pom.xml .
COPY src ./src
# Ejecutamos la compilación
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copiamos solo el archivo .jar generado en la etapa anterior
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]