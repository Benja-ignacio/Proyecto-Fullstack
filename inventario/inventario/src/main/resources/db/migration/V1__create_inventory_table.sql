CREATE TABLE inventory (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    product_id BIGINT NOT NULL UNIQUE,
    total_quantity INT NOT NULL DEFAULT 0,
    available_quantity INT NOT NULL DEFAULT 0,
    reserved_quantity INT NOT NULL DEFAULT 0,

    -- no negativos
    CHECK (total_quantity >= 0),
    CHECK (available_quantity >= 0),
    CHECK (reserved_quantity >= 0),

    -- stock total = disponible + reservada
    CHECK (total_quantity = available_quantity + reserved_quantity)
);