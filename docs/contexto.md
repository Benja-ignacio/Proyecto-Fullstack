# Contexto general del sistema

Este documento describe el funcionamiento general del sistema y sus microservicios.
Su objetivo es dar contexto a desarrolladores y herramientas (IA) para evitar errores de diseño.

## Contexto del proyecto

Aura Shop es una tienda online dedicada a vender cosas "gamers"

El proyecto actualmente cuenta con 10 microservicios que se comunican entre sí mediante APIs.

Los microservicios son los siguientes:

- **users**: autenticacion, registro y gestion de usuarios
- **products**: catalogo de productos
- **inventory**: control de stock
- **cart**: gestion del carrito de compras
- **orders**: creacion y gestion de pedidos
- **payment**: procesamiento de pagos
- **logistics**: gestion de envios y estados del pedido
- **discounts**: manejo de cupones y descuentos
- **notifications**: envio de notificaciones
- **feedback**: comentarios y valoraciones de usuarios

las entidades estan modeladas de la siguiente manera:

### users

table users:

- user_id,
- username,
- password,
- email,
- address,
- role,
- status (enum),
- created_at

status enums: CLIENT, ADMIN

### products

table products:

- product_id,
- sku,
- name,
- type,
- price,
- description,
- status (enum)

status enums: ACTIVE, INACTIVE, COMING_SOON, DISCONTINUED

- ACTIVE: visible y vendible
- INACTIVE: oculto/deshabilitado
- COMING_SOON: aún no disponible
- DISCONTINUED: descontinuado

type enums: KEYBOARD, MOUSE, MONITOR, MOUSEPAD, PROCESSOR, GPU, HEADSET

## NOTA

Los enums de type pueden expandirse según las necesidades del catálogo.

### inventory

table inventory:

- id,
- product_id (referencia a product service),
- total_quantity,
- available_quantity,
- reserved_quantity

### cart

table cart:

- id,
- user_id

table cart_item:

- id,
- cart_id (fk interna),
- product_id (referencia a product service),
- product_name,
- price,
- quantity

### orders

table orders:

- id,
- user_id (referencia a user service),
- subtotal,
- discount,
- shipping,
- total,
- status,
- created_at,
- paid_at

status enums: PENDING, PAID, CANCELED

PENDING → al crear la orden
PAID → cuando payment aprueba el pago
CANCELED → cuando el pago falla o el usuario cancela

**relacion 1:N con order_item**

table order_item

- id,
- order_id (fk interna),
- product_id (referencia a product service),
- product_name,
- price,
- quantity

### logistics

table logistics:

- id,
- order_id (referencia a order service),
- distance,
- shipping,
- status,
- expected_delivery_date,
- shipped_at,
- delivered_at

status enums: DELIVERED, CANCELED, PENDING, SHIPPED

### discounts

table discounts:

- id,
- code,
- description,
- type,
- product_type,
- value,
- min_purchase_amount,
- max_discount_amount,
- usage_limit,
- used_count,
- usage_limit_per_user,
- start_date,
- end_date,
- active

type ENUMS: PERCENTAGE, FIXED_AMOUNT

Product_type enums: MOUSE, KEYBOARD,...etc.

### notifications

table notifications:

- id,
- user_id (referencia a user service),
- title,
- message,
- created_at,
- read

### feedback

table feedback:

- id,
- user_id (referencia a user service),
- product_id (referencia a product service),
- rating,
- title,
- comment,
- created_at

### payment

table payment

- id
- order_id (referencia a order service)
- user_id (referencia a user service)
- status
- card_last_4
- payment_method
- transaction_id
- amount
- created_at

status enums: FAILED, APPROVED, PENDING

payment_method enums: CARD, TRANSFER

## NOTA

**cada microservicio cuenta con su propia base de datos y tablas**

No existen relaciones directas (FK) entre bases de datos. La comunicación se realiza mediante APIs.

## Tecnologías

- Java
- Spring Boot
- MySQL
- Docker
- Maven

## Convenciones

- Los IDs se manejan con tipo Long
- Los precios y montos usan BigDecimal
- Los estados se representan mediante enums
- Las fechas usan LocalDateTime
- Las relaciones entre microservicios son referencias lógicas mediante IDs
- Las relaciones internas pueden usar FK reales

## Snapshot

Cart y OrderItem almacenan snapshots de productos.

Esto significa que se guarda una copia del nombre y precio del producto
en el momento de la acción para evitar inconsistencias futuras.

## Puertos de los servicios

| Service       | API Port | MySQL Host Port | MySQL Container Port |
| ------------- | -------: | --------------: | -------------------: |
| users         |     8080 |            3310 |                 3306 |
| products      |     8081 |            3311 |                 3306 |
| cart          |     8082 |            3312 |                 3306 |
| feedback      |     8083 |            3313 |                 3306 |
| inventory     |     8084 |            3314 |                 3306 |
| payment       |     8085 |            3315 |                 3306 |
| order         |     8086 |            3316 |                 3306 |
| logistic      |     8087 |            3317 |                 3306 |
| discounts     |     8088 |            3318 |                 3306 |
| notifications |     8089 |            3319 |                 3306 |

## Responsabilidades de los servicios

### users

responsable de:

- autenticacion
- autorizacion
- JWT
- gestion de usuarios

NO responsable de:

- pedidos
- pagos
- productos
- pago
- stock

El servicio de usuarios es el encargado de gestionar los usuarios(ID, username, password, email, address). Tambien es el encargado de la autenticacion y autorizacion con tokens, la implementacion de JWT.

**Authentication**

- Se utiliza JWT Bearer Token
- BCrypt para hashing de contraseñas
- Los microservicios validan JWT
- El servicio users es responsable de emitir tokens

## API conventions

- Respuestas envueltas en ApiResponse
- IDs tipo Long
- Base path: /api/v1

# Endpoints

## API Users

Base URL: /api/v1/users

- Registrar usuario

Endpoint: POST /register

Permite registrar un nuevo usuario en el sistema.

- Login de usuario

Endpoint: POST /login

Permite autenticar un usuario y obtener un token.

- Listar usuarios

Endpoint: GET /list

Obtiene todos los usuarios registrados.

- Validar token

Endpoint: GET /validate?token={token}

Permite validar si un token JWT es válido.

## API Products

Base URL: /api/v1/products

- Crear producto

Endpoint: POST /create

Crea un nuevo producto.

- Buscar producto por ID

Endpoint: GET /{id}

Obtiene un producto según su ID.

- Listar productos

Endpoint: GET /list

Obtiene todos los productos registrados.

- Actualizar producto

Endpoint: PUT /update/{id}

Actualiza un producto existente.

- Eliminar producto

Endpoint: DELETE /delete/{id}

Deshabilita un producto.

- Cambiar estado de producto

Endpoint: PATCH /update/status/{id}?status={status}

Permite modificar el estado del producto.

## API Notifications

Base URL: /api/v1/notification

- Listar notificaciones

Endpoint: GET /

Obtiene todas las notificaciones.

- Buscar notificación por ID

Endpoint: GET /{id}

Obtiene una notificación por ID.

- Buscar notificaciones por usuario

Endpoint: GET /user/{userId}

Obtiene todas las notificaciones de un usuario.

- Crear notificación

Endpoint: POST /create

Crea una nueva notificación.

- Marcar notificación como leída

Endpoint: PUT /read/{id}

Marca una notificación como leída.

- Eliminar notificación

Endpoint: DELETE /delete/{id}

Elimina una notificación.

## API Inventory

Base URL: /api/v1/inventory

- Crear inventario

Endpoint: POST /create

Crea un registro de inventario.

- Buscar inventario por ID

Endpoint: GET /{id}

Obtiene un inventario por ID.

- Buscar inventario por productId

Endpoint: GET /product/{productId}

Obtiene el inventario asociado a un producto.

- Listar inventarios

Endpoint: GET /

Obtiene todos los inventarios.

- Actualizar inventario

Endpoint: PUT /update/{id}

Actualiza un inventario.

- Eliminar inventario

Endpoint: DELETE /delete/{id}

Elimina un inventario.

## API Feedback

Base URL: /api/v1/feedback

- Crear feedback

Endpoint: POST /create

Crea un feedback para un producto.

- Buscar feedbacks por producto

Endpoint: GET /product/{productId}

Obtiene todos los feedbacks de un producto.

- Buscar feedbacks por usuario

Endpoint: GET /user/{userId}

Obtiene todos los feedbacks realizados por un usuario.

- Buscar feedback por ID

Endpoint: GET /{id}

Obtiene un feedback por ID.

- Eliminar feedback

Endpoint: DELETE /delete/{id}

Elimina un feedback.

- Actualizar feedback

Endpoint: PUT /update/{id}

Actualiza un feedback existente.

## API Discounts

Base URL: /api/v1/discount

- Crear descuento

Endpoint: GET /create

Crea un nuevo descuento.

- Actualizar descuento

Endpoint: PATCH /update/{id}

Actualiza un descuento existente.

- Activar / desactivar descuento

Endpoint: PATCH /activate/{id}?active=true

Activa o desactiva un descuento.

- Eliminar descuento

Endpoint: DELETE /delete/{id}

Elimina un descuento.

- Buscar descuento por ID

Endpoint: GET /{id}

Obtiene un descuento por ID.

- Buscar descuentos por usuario

Endpoint: GET /user/{id}

Obtiene descuentos usados por un usuario.
