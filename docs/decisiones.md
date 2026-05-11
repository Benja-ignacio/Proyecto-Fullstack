# Decisiones y criterios de diseño del proyecto

Este documento registra las decisiones técnicas tomadas en el proyecto, junto con su contexto y justificación.

Su objetivo es mantener consistencia y evitar rehacer decisiones ya tomadas.

### Decisiones globales

- No se usan FK entre microservicios para mantener independencia entre servicios.

- Se permite duplicación controlada para reducir dependencias y preservar información histórica.

- Payment será simulado para evitar complejidad innecesaria.

### discounts

Se permite null en ciertos atributos de discounts para soportar distintos tipos de promociones sin complejizar el modelo.

### Reglas

- product_type null → descuento global
- end_date null → sin fecha de expiración
- usage_limit null → usos ilimitados
- min_purchase_amount null → sin compra mínima
