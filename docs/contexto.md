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
- max_purchase_amount,
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
