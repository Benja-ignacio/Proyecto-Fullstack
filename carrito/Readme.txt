Microservicio encargado de la gestión del carrito de compras, permitiendo agregar, eliminar y actualizar productos, gestionar cantidades y calcular el total previo a la generación del pedido


# Cart Microservice

Este microservicio corresponde a la gestión del carrito de compras dentro del sistema Aura Shop, una tienda online orientada a productos gamer.

Su función principal es permitir que cada usuario pueda administrar su carrito de compras mediante su userId, agregando productos, actualizando cantidades, eliminando productos y calculando el total de la compra.

Se implementó la entidad Cart para asociar el carrito a cada usuario y CartItem para almacenar los productos agregados. Cada item guarda un snapshot del producto, incluyendo productName y price, evitando inconsistencias si el catálogo cambia posteriormente.

El microservicio utiliza Spring Data JPA junto con MySQL para la persistencia de datos, manteniendo una base de datos independiente como parte de la arquitectura de microservicios.

También se incorporaron DTOs para desacoplar la API de las entidades JPA, ResponseEntity y ApiResponse para respuestas REST consistentes, validaciones con Bean Validation, manejo global de excepciones mediante GlobalExceptionHandler y logs para mejorar el monitoreo del sistema.

Para permitir la futura comunicación entre microservicios como Products, Users e Inventory, se agregó WebClient mediante la dependencia spring-boot-starter-webflux en el pom.xml, junto con la configuración WebClientConfig para realizar llamadas entre servicios de forma moderna y escalable.

Además, se agregaron dependencias como spring-boot-starter-web, spring-boot-starter-data-jpa, mysql-connector-j, spring-boot-starter-validation, spring-boot-starter-security y Lombok para asegurar el correcto funcionamiento del microservicio.

Finalmente, se dejó preparada la estructura para Docker Compose para futuras bases de datos independientes por servicio.
