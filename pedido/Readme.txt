# Order Microservice

Este microservicio corresponde a la gestión de pedidos dentro del sistema Aura Shop, una tienda online orientada a productos gamer.

Su objetivo principal es generar y administrar órdenes de compra a partir de los productos seleccionados en el carrito.

Se implementó la entidad Order para almacenar información como subtotal, descuentos, costo de envío, total final, estado del pedido y fechas importantes como creación y pago. Además, se utiliza OrderItem para guardar el detalle individual de cada producto comprado.

Cada pedido mantiene snapshot de los productos mediante productName y price, asegurando consistencia incluso si el catálogo cambia posteriormente.

Se utilizó Spring Data JPA con MySQL para la persistencia, manteniendo una base de datos independiente por microservicio, respetando la arquitectura distribuida del sistema.

También se incorporaron DTOs, ResponseEntity, ApiResponse, validaciones con Bean Validation, manejo global de excepciones, logs y configuración con WebClient para futura integración con Payment, Users, Logistics y otros microservicios del sistema.

Para implementar WebClient fue necesario agregar en el pom.xml la dependencia spring-boot-starter-webflux, además de spring-boot-starter-web para los controladores REST, permitiendo una correcta comunicación entre microservicios y preparando la integración futura del sistema completo.

También se añadieron dependencias como spring-boot-starter-data-jpa, mysql-connector-j, spring-boot-starter-validation, spring-boot-starter-security y Lombok para mantener una arquitectura limpia, escalable y profesional.

Finalmente, se dejó preparada la estructura para Docker Compose, permitiendo un despliegue más profesional y escalable.
