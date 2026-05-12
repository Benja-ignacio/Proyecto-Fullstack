package cl.duoc.productos.dto;


import java.math.BigDecimal;

import cl.duoc.productos.enums.Status;
import cl.duoc.productos.enums.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String sku;
    private String name;
    private String brand;

    private Type type;

    private BigDecimal price;
    private String description;
    private Status status;
}
