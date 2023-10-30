# Usa una imagen base de Java 8
FROM openjdk:8-jre-slim
# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app
# Copia el archivo JAR de tu proyecto al contenedor
COPY target/between-tech.jar app.jar
# Expone el puerto en el que tu aplicación Spring Boot se ejecuta (ajusta según sea necesario)
EXPOSE 8080
# Comando para ejecutar la aplicación Spring Boot
CMD ["java", "-jar", "app.jar"]