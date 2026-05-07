# diagramas de flujos

Este documento contiene diagramas de flujo de diferentes servicios

**Flujo: compra de producto**

1. user -> agrega producto a cart
2. cart -> guarda snapshot
3. order -> se crea
4. inventory -> reserva stock
5. logistics -> genera envío

## NOTA

Snapshot se refiere a guardar una copia de los datos en un momento específico.

En este caso, cuando el usuario agrega un producto al carrito, se guarda una copia del nombre y precio del producto.

Esto es importante porque esos datos pueden cambiar en el futuro.
Por ejemplo, el precio de un producto puede aumentar o el nombre puede modificarse.

Gracias al snapshot, el carrito mantiene los valores originales, evitando inconsistencias.

### Ejemplo

Un usuario agrega un "Mouse Logitech G203" con precio $10.000 a su carrito.

Luego, el precio del producto cambia a $12.000 en el sistema.

El carrito seguirá mostrando el producto con el precio original ($10.000),
ya que se guardó un snapshot en el momento en que fue agregado.

## NOTA 2

**No todos los datos necesitan snapshot**

### ¿Qué datos sí guardar?

- precio (afecta dinero)
- nombre del producto (lo que ve el usuario)

---

## ¿Qué pasa si el precio cambia antes de pagar?

Si el usuario aún no ha pagado y el precio cambia, el sistema debe validar el precio al momento del checkout.

### ¿Qué es el checkout?

El checkout es el proceso en el que el usuario confirma la compra y procede al pago.

### Flujo

1. Usuario hace checkout
2. El sistema consulta el precio actual del producto
3. Se compara con el precio guardado en el snapshot
4. Si el precio cambió:
   - se actualiza el carrito o
   - se informa al usuario antes de continuar

---
