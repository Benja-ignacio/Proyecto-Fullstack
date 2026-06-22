AURA SHOP - Arquitectura de Microservicios
Descripción del proyecto

AURA SHOP es una plataforma backend orientada a la venta de periféricos y productos gamer. El sistema fue desarrollado bajo una arquitectura de microservicios, separando las responsabilidades principales del negocio en servicios independientes.

El objetivo del proyecto es implementar una solución distribuida que permita gestionar usuarios, productos, inventario, carrito de compras, pedidos, pagos, logística, descuentos, notificaciones y feedback, integrando comunicación REST, API Gateway, Eureka Server, bases de datos MySQL, Swagger/OpenAPI y pruebas unitarias.

Integrantes
Benjamin Perez
Elias Carcamo
Ignacio Martinez
Germán Pino
Tecnologías utilizadas
Java 21
Spring Boot
Spring Data JPA
Spring Cloud Gateway
Eureka Server
MySQL
Docker Desktop
Maven
JUnit 5
Mockito
Swagger / OpenAPI
Postman
GitHub
Microservicios del sistema
Microservicio	Función principal
Eureka Server	Registro y descubrimiento de microservicios
API Gateway	Punto de entrada centralizado del sistema
Usuarios	Registro, login, roles y autenticación con JWT
Productos	Administración del catálogo de productos
Inventario	Control de stock y disponibilidad
Carrito	Gestión de productos seleccionados por el usuario
Pedidos	Creación y administración de órdenes de compra
Pagos	Registro y validación de pagos
Logística	Gestión de envíos y estados de despacho
Descuentos	Administración de cupones y promociones
Notificaciones	Gestión de alertas y mensajes del sistema
Feedback	Registro de comentarios y valoraciones
Arquitectura general

El sistema utiliza una arquitectura distribuida basada en microservicios. Cada microservicio mantiene su propia lógica de negocio, estructura por capas y conexión a base de datos, evitando el acoplamiento directo entre módulos.

Eureka Server permite registrar los servicios activos y API Gateway centraliza el acceso a las rutas del sistema. La comunicación entre microservicios se realiza mediante llamadas REST internas usando WebClient o Feign Client, según la implementación de cada módulo.

Puertos principales
Componente	Puerto
Eureka Server	8761
API Gateway	9999
Usuarios	8080
Carrito	8082
Pedidos	8086
MySQL Users	3310
MySQL Products	3311
MySQL Cart	3312
MySQL Feedback	3313
MySQL Inventory	3314
MySQL Payment	3315
MySQL Order	3316
MySQL Discounts	3318
MySQL Notifications	3319
Rutas principales del API Gateway
Servicio	Ruta Gateway
Autenticación	http://localhost:9999/api/v1/auth/**
Usuarios	http://localhost:9999/api/v1/users/**
Productos	http://localhost:9999/api/v1/products/**
Inventario	http://localhost:9999/api/v1/inventory/**
Carrito	http://localhost:9999/api/cart/**
Pedidos	http://localhost:9999/api/v1/orders/**
Pagos	http://localhost:9999/api/v1/payments/**
Logística	http://localhost:9999/api/v1/logistics/**
Descuentos	http://localhost:9999/api/v1/discounts/**
Notificaciones	http://localhost:9999/api/v1/notifications/**
Feedback	http://localhost:9999/api/v1/feedback/**
Documentación Swagger / OpenAPI

Cada microservicio cuenta con documentación Swagger/OpenAPI para visualizar endpoints, métodos HTTP, parámetros, cuerpos de solicitud y respuestas esperadas.

Ejemplos de acceso local:

Servicio	Swagger
Usuarios	http://localhost:8080/swagger-ui/index.html
Carrito	http://localhost:8082/swagger-ui/index.html
Pedidos	http://localhost:8086/swagger-ui/index.html
Otros servicios	http://localhost:PUERTO/swagger-ui/index.html
Ejecución local del proyecto
1. Levantar bases de datos

Abrir Docker Desktop y levantar los contenedores MySQL correspondientes a los microservicios.

Para verificar los contenedores activos:

docker ps
2. Levantar Eureka Server
cd eureka-server
./mvnw spring-boot:run

Verificar en:

http://localhost:8761
3. Levantar los microservicios

Ingresar a la carpeta de cada microservicio y ejecutar:

./mvnw spring-boot:run

Ejemplo:

cd usuarios/usuarios
./mvnw spring-boot:run
cd carrito/carrito
./mvnw spring-boot:run
cd pedido/pedido
./mvnw spring-boot:run
4. Levantar API Gateway
cd gateway/gateway
./mvnw spring-boot:run

Verificar acceso mediante:

http://localhost:9999
Pruebas unitarias

El proyecto integra pruebas unitarias con JUnit y Mockito para validar la lógica de negocio de los microservicios. Las pruebas se encuentran dentro de la carpeta src/test/java de cada servicio.

Para ejecutar pruebas:

./mvnw test

Resultado esperado:

Tests run
Failures: 0
Errors: 0
BUILD SUCCESS
Ejemplos de endpoints principales
Login
POST http://localhost:9999/api/v1/auth/login
Registro de usuario
POST http://localhost:9999/api/v1/auth/register
Consultar productos
GET http://localhost:9999/api/v1/products
Consultar inventario
GET http://localhost:9999/api/v1/inventory
Consultar carrito
GET http://localhost:9999/api/cart?userId=1
Crear pedido
POST http://localhost:9999/api/v1/orders/create?userId=1
Consultar pagos
GET http://localhost:9999/api/v1/payments
Consultar notificaciones
GET http://localhost:9999/api/v1/notifications
Trabajo colaborativo

El proyecto fue gestionado mediante GitHub, utilizando ramas, commits y Pull Requests para integrar los avances del equipo. Cada integrante trabajó en microservicios o componentes específicos del ecosistema, manteniendo control de versiones y evidencias de participación.

Estado del proyecto

El sistema cuenta con una arquitectura de microservicios organizada bajo el patrón CSR, integración con Eureka Server, API Gateway, configuración YAML, bases de datos MySQL, documentación Swagger/OpenAPI, pruebas unitarias y validación de endpoints mediante Postman.