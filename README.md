# AgroTrack — Backend

Backend REST API del proyecto AgroTrack, desarrollado con Spring Boot. Provee los endpoints que consume el frontend Angular, reemplazando el MockAPI del Sprint 2 con una base de datos MySQL real y validaciones de negocio.

----

## Cómo correr el proyecto

1. Clonar el repositorio
2. Abrir el proyecto en tu IDE
3. En `src/main/resources/application.properties`, reemplazar con tus credenciales locales de MySQL:
   ```properties
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   ```
4. Correr `AgrotrackApplication.java`
5. La base de datos `agrotrack` se crea automáticamente si no existe
6. Las tablas también se crean automáticamente al iniciar

---

## Probar los endpoints

Una vez corriendo, abrir en el navegador:

```
http://localhost:8080/api/v1/swagger-ui/index.html
```

Ahí puedes ver y probar todos los endpoints disponibles.

---

## Estructura del proyecto

```
org.example.agrotrack
├── shared/                        → Clases compartidas (NO modificar)
│   ├── aggregates/                → Base para aggregate roots de dominio
│   ├── result/                    → ApplicationError y Result<T,E>
│   ├── infrastructure/
│   │   ├── persistence/           → Base para entidades JPA (UUID id)
│   │   ├── documentation/         → Configuración de Swagger/OpenAPI
│   │   └── i18n/                  → Soporte de idiomas (EN/ES)
│   └── interfaces/
│       ├── resources/             → ErrorResource, MessageResource
│       └── transform/             → ErrorResponseAssembler, ResponseEntityAssembler
│
├── identity/                      → BC: Usuarios, planes, autenticación
├── farming/                       → BC: Parcelas y cultivos
├── soilmonitoring/                → BC: Registros de suelo y riego
├── alerts/                        → BC: Alertas climáticas
├── dashboard/                     → BC: Dashboard pro | empresarial
└── support/                       → BC: Tickets de soporte
```
