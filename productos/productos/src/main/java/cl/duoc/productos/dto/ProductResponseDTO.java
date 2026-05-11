package cl.duoc.productos.dto;


import java.math.BigDecimal;

import cl.duoc.productos.enums.Status;
import cl.duoc.productos.enums.Type;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    
    
    private Long id;

    private String sku;

    private String name;

    private Type type;

    private BigDecimal price;

    private String description;

    private Status status;
}
