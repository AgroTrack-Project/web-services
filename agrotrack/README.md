# AgroTrack — Web Service (Backend)

API REST del ecosistema AgroTrack, desarrollada con Spring Boot. Expone los endpoints que consume la Web Application de AgroTrack, gestionando la persistencia de parcelas, cultivos, monitoreo del suelo, alertas climáticas e identidad de usuarios sobre una base de datos MySQL real, reemplazando el MockAPI utilizado en etapas iniciales del proyecto.

Web Service (Swagger UI): [https://agotrack.onrender.com/api/v1/swagger-ui/index.html](https://agotrack.onrender.com/api/v1/swagger-ui/index.html)

Web Application: [https://agro-track.vitaltrek.workers.dev/](https://agro-track.vitaltrek.workers.dev/)

---

## Descripción

Este repositorio contiene el Web Service de AgroTrack, un RESTful API desarrollado bajo el estilo arquitectónico REST con Spring Boot y Java. El servicio centraliza la lógica de negocio y la persistencia de datos de la solución, exponiendo endpoints documentados mediante OpenAPI Specification (Swagger), consumidos por la Web Application desarrollada en Angular.

---

## Tecnología

- **Java** — lenguaje de programación del servicio.
- **Spring Boot** — framework principal para el desarrollo del API REST.
- **MySQL** — motor de base de datos relacional, alojado en Aiven.
- **OpenAPI Specification (Swagger)** — documentación interactiva de los endpoints.
- **OpenWeather API** — servicio externo de terceros integrado para el bounded context de alertas climáticas.
- **Docker** — contenerización del servicio para su despliegue.
- **Render** — plataforma de hosting del backend en producción.

---

## Instalación y ejecución local

1. Clonar el repositorio.
2. Abrir el proyecto en tu IDE de preferencia.
3. En `src/main/resources/application.properties`, reemplazar con tus credenciales locales de MySQL:

   ```properties
   spring.datasource.username={YOUR_USER}
   spring.datasource.password={YOUR_PASSWORD}
   ```

4. En el mismo archivo, reemplazar la API key de OpenWeather con la tuya:

   ```properties
   openweather.api.key={YOUR_API_KEY}
   ```

   Puedes generar tu API key gratuita en [https://openweathermap.org/](https://openweathermap.org/), registrándote y accediendo a **API keys** en tu perfil.

5. Ejecutar la clase principal `AgrotrackApplication.java`.
6. La base de datos `agrotrack` se crea automáticamente si no existe.
7. Las tablas se generan automáticamente al iniciar la aplicación.

### Solución de problemas

**No aparece el botón de ejecutar (triángulo verde) en IntelliJ IDEA**

1. Clic derecho sobre `pom.xml` → **Maven → Sync project**.
2. Esperar a que IntelliJ descargue todas las dependencias y reindexe el proyecto.

---

## Documentación y prueba de endpoints

Una vez iniciado el servicio localmente, la documentación interactiva de los endpoints está disponible en:

```
http://localhost:8080/api/v1/swagger-ui/index.html
```

En producción, la documentación está disponible en:

[https://agotrack.onrender.com/api/v1/swagger-ui/index.html](https://agotrack.onrender.com/api/v1/swagger-ui/index.html)

---

## Despliegue

El servicio se despliega mediante contenedor **Docker** sobre **Render**, utilizando **Aiven** como proveedor de la base de datos MySQL en la nube. Las credenciales de conexión y la API key de OpenWeather se gestionan mediante variables de entorno en el entorno de producción.

---

## Control de versiones y colaboración

Este repositorio sigue las siguientes convenciones para mantener consistencia en el ciclo de vida del producto:

- GitFlow como workflow de branching (`main`, `develop`, `feature/*`, `release/*`, `hotfix/*`).
- Conventional Commits para los mensajes de commit (`feat:`, `fix:`, `chore:`, etc.).
- Semantic Versioning para el nombrado de releases.
 
---