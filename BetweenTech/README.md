# Prueba Técnica para Between Technology

## Descripción

Este proyecto es una aplicación Java basada en la arquitectura hexagonal, desarrollada en Java 1.8 y utiliza diversas tecnologías para crear una solución robusta y eficiente. A continuación, se describen los aspectos clave del proyecto.

## Detalles del Proyecto

- **Arquitectura Hexagonal**: El proyecto se adhiere a la arquitectura hexagonal, una metodología de diseño que separa claramente las capas de negocio y la infraestructura. Esto proporciona un código modular y fácil de mantener.

- **Lombok para Mayor Claridad del Código**: Se emplea Lombok, una biblioteca de Java, para eliminar la necesidad de escribir código repetitivo como getters, setters y constructores. Esto resulta en un código más limpio y fácil de entender.

- **Pruebas Unitarias con Mockito**: El código se somete a pruebas unitarias exhaustivas utilizando Mockito. Esto garantiza que la lógica del programa funcione como se espera y facilita las futuras actualizaciones.

- **Base de Datos H2**: La base de datos se implementa mediante H2, una base de datos en memoria. Esto permite almacenar datos en tiempo de ejecución sin necesidad de una base de datos externa. Esta elección es particularmente útil para pruebas y desarrollo rápido.

- **Documentación con Swagger**: Swagger se emplea para documentar y exponer los servicios RESTful. Proporciona una interfaz interactiva que facilita la comprensión y el consumo de los servicios. La documentación de Swagger está disponible en:

  [http://localhost:8080/between-service/swagger-ui.html](http://localhost:8080/between-service/swagger-ui.html)

- **Docker para la Portabilidad**: El proyecto incluye un archivo Docker para ejecutar la aplicación en un contenedor Docker. Los comandos para construir y ejecutar la imagen del contenedor son los siguientes:

    ```bash
    docker build -t between-tech:latest .
    docker run -d -p 8080:8080 between-tech:latest
    ```

  Esto permite que la aplicación sea fácilmente transportable y ejecutable en diferentes entornos.

- **Ejecución con `java -jar`**: La aplicación se puede ejecutar localmente utilizando el comando `java -jar` después de compilarla con Maven. Ejemplo:

    ```bash
    java -jar target/between-tech.jar
    ```

## Requisitos

Asegúrate de tener instalados los siguientes componentes antes de ejecutar el proyecto:

- Java 1.8
- Docker (opcional para ejecutar en contenedor)

## Instalación

Sigue estos pasos para ejecutar la aplicación en tu entorno local:

1. Clona este repositorio https://github.com/nivrist/between-tech-test-java.git  a tu máquina local.
2. Asegúrate de tener Java 1.8 instalado.
3. Utiliza Maven para compilar y construir el proyecto.

    ```bash
    mvn clean install
    ```

4. Si deseas ejecutar la aplicación en un contenedor Docker, asegúrate de tener Docker instalado y ejecuta los comandos de Docker mencionados anteriormente.

Con estos pasos, tendrás el proyecto en funcionamiento en tu entorno local.