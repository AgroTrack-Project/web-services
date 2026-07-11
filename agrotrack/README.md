# AgroTrack — Backend

Backend REST API del proyecto AgroTrack, desarrollado con Spring Boot. Provee los endpoints que consume el frontend Angular, reemplazando el MockAPI del Sprint 2 con una base de datos MySQL real y validaciones de negocio.

----

## Cómo correr el proyecto

1. Clonar el repositorio
2. Abrir el proyecto en tu IDE
3. En `src/main/resources/application.properties`, reemplazar con tus credenciales locales de MySQL:
   ```properties
   spring.datasource.username={YOUR_USER}
   spring.datasource.password={YOUR_PASSWORD}
   ```
4. En el mismo archivo, reemplazar la API key de OpenWeather con la tuya:
   ```properties
   openweather.api.key={YOUR_API_KEY}
   ```
   Puedes generar tu API key gratis en [https://openweathermap.org/](https://openweathermap.org/) registrándote y yendo a **API keys** en tu perfil.
5. Correr `AgrotrackApplication.java`
6. La base de datos `agrotrack` se crea automáticamente si no existe
7. Las tablas también se crean automáticamente al iniciar

---

## Solución de posibles problemas

### No aparece el botón de ejecutar (triángulo verde)

Si al abrir el proyecto en IntelliJ IDEA no aparece el triángulo verde para ejecutar:

1. Haz clic derecho en el `pom.xml` → **Maven → Sync project**
2. Espera a que IntelliJ descargue todas las dependencias y reindexe el proyecto

---

## Probar los endpoints

Una vez corriendo, abrir en el navegador:

```
http://localhost:8080/api/v1/swagger-ui/index.html
```

Ahí puedes ver y probar todos los endpoints disponibles.

---
