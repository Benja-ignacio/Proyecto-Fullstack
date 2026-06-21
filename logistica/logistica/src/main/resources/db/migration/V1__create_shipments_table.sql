CREATE TABLE IF NOT EXISTS shipments (
    shipment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    inventory_id BIGINT NOT NULL,          -- Referencia lógica al microservicio de Inventario
    tracking_number VARCHAR(100) NOT NULL UNIQUE, -- Código de seguimiento (ej: TRK-12345)
    courier_name VARCHAR(100) NOT NULL,     -- Empresa de transporte (Starken, Chilexpress, etc.)
    delivery_address VARCHAR(255) NOT NULL, -- Dirección de destino
    status VARCHAR(50) NOT NULL,           -- PENDIENTE, EN_CAMINO, ENTREGADO, CANCELADO
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);