# 1. Imagem base que existe e funciona (Java 17)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# 2. Copia o seu jar para dentro da imagem (mantive o seu caminho)
COPY target/messaging-springboot-0.0.1-SNAPSHOT.jar app.jar

# 3. Comando para rodar (mantive o seu)
CMD ["java", "-jar", "app.jar"]