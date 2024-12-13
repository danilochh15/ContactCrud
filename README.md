# ContactCrud Project

Este es un proyecto en Java para gestionar contactos utilizando SwingX para la interfaz gráfica. El proyecto está configurado con Maven para facilitar la gestión de dependencias y la compilación.

## Requisitos

Antes de comenzar, asegúrate de tener las siguientes herramientas instaladas:

- **Java 17**: Este proyecto está configurado para usar Java 17.
    - Verifica que tienes Java 17 instalado con el siguiente comando:
        ```bash
        java -version
        ```
    - Si no tienes Java 17, puedes descargarlo desde el [sitio oficial de Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).

- **Maven**: Este proyecto utiliza Maven para gestionar las dependencias y la construcción del proyecto.
    - Verifica que tienes Maven instalado con:
        ```bash
        mvn -v
        ```
    - Si Maven no está instalado, puedes seguir las instrucciones de instalación en [la página oficial de Maven](https://maven.apache.org/install.html).

## Pasos para compilar y ejecutar el proyecto

### 1. Clonar el repositorio

Si aún no tienes el proyecto en tu máquina local, clónalo con Git:

```bash
git clone https://github.com/danilochh15/ContactCrud
cd contactcrud
```

### 2. Ir al directorio raiz del proyecto

Ejecutar los comandos
   ```bash
      mvn clean install
      mvn exec:java
  ```
