# Tareas por hacer

Documento que contiene tareas que se deben de completar

## Tareas pendientes

[HIGH]

- crear reglas de negocio (ej: El precio final se valida en checkout)
- implementar permisos basados en roles
- implementar autorización con Spring Security
- proteger endpoints según rol (CLIENT / ADMIN)
- definir endpoints públicos y privados
- validar acceso mediante JWT
- terminar el servicio users
- validar servicios
- agregar configuraciones de seguridad a los servicios
- crear flujo de la compra backend (creacion de compra init, confirmar pago)
- terminar el DockerCompose masivo

[MEDIUM]

- validar stock antes de checkout
- implementar metodo para cambiar contraseña del usuario (un cambio por mes)
- implementar metodo para cambiar nombre del usuario (un cambio por mes)
- manejar expiración de carrito
- usar apiresponse en todos los servicios
- agregar reglas de negocio a contexto.md
- agregar responsabilidades de los servicios a contexto.md
- agregar DTOs principales de los servicios a contexto.md
- agregar flujos de los servicios a contexto.md
- agregar como se comunica cada servicio a contexto.md
- documentar roles y permisos en contexto.md
- definir qué endpoints requieren ADMIN
- definir qué endpoints pueden usar CLIENT
- terminar validaciones en
- crear funcion refunded en pago
- agregar logs

[LOW]

- Lista de los productos que tendra el sistema
- evitar duplicados en cart_item
- Mapear created_at y updated_at en entidad Product
- Agregar timestamps a responses DTO
- agregar swagger
- Agregar metodos para calcular tiempo antes de que se cancela un pedido(ej: >15 minutos sin pagar = pedido cancelado) y alguna forma de calcular ExpectedDeliveryDate
