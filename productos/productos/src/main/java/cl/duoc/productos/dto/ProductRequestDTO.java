package cl.duoc.productos.dto;


import java.math.BigDecimal;

import cl.duoc.productos.enums.Status;
import cl.duoc.productos.enums.Type;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class ProductRequestDTO {


    @NotBlank(message = "El sku no puede estar vacio")
    private String sku;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @NotNull(message = "El tipo de producto no puede estar vacio")
    private Type type;

    @NotNull(message = "El precio o puede estar vacio")
    @DecimalMin(value = "0.0", inclusive = false,
        message = "El precio no puede ser menor a 0")
    private BigDecimal price;

    @NotBlank(message = "Debe añadir una descripcion")
    private String description;
    
    @NotNull
    private Status status;

}
