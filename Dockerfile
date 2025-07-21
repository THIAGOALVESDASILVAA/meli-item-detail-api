# Dockerfile para Meli Item Detail API (REST Adapter)
FROM openjdk:17-jdk-slim

# Metadados da imagem
LABEL maintainer="thiago.alves@mercadolibre.com"
LABEL description="Meli Item Detail API - Hexagonal Architecture"
LABEL version="1.0-SNAPSHOT"

# Definir argumentos de build
ARG JAR_FILE=rest-adapter/target/meli-item-detail-rest-adapter-1.0-SNAPSHOT.jar
ARG APP_DIR=/app

# Criar diretório da aplicação
WORKDIR ${APP_DIR}

# Criar usuário não-root para segurança
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

# Instalar dependências do sistema (opcional, para troubleshooting)
RUN apt-get update && apt-get install -y \
    curl \
    net-tools \
    && rm -rf /var/lib/apt/lists/*

# Copiar o JAR da aplicação
COPY ${JAR_FILE} app.jar

# Copiar recursos adicionais se necessário
COPY rest-adapter/src/main/resources/application.yml ./config/
COPY infrastructure/src/main/resources/mock-data/ ./mock-data/

# Criar diretório de logs
RUN mkdir -p /app/logs

# Alterar ownership para usuário não-root
RUN chown -R appuser:appgroup ${APP_DIR}

# Trocar para usuário não-root
USER appuser

# Expor porta da aplicação
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# Variáveis de ambiente
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC"
ENV SPRING_PROFILES_ACTIVE=rest-adapter
ENV SERVER_PORT=8080

# Comando de inicialização
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]
