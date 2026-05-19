package cl.duoc.productos.dto;

import java.math.BigDecimal;

import cl.duoc.productos.enums.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDTO {
    
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 256)
    private String name;
    
    @NotBlank(message = "La marca no puede estar vacia")
    @Size(min = 2, max = 256)
    private String brand;

    @NotNull(message = "El tipo de producto no puede ser nulo")
    private Type type;

    @NotNull(message = "El precio no puede estar vacio")
    @DecimalMin(value = "0.0", inclusive = false, // inclusive false > 0.0, True >= 0.0
    message = "El precio no puede ser menor a 0")
    private BigDecimal price;

    @NotBlank(message = "Debe añadir una descripcion")
    @Size(min = 2, max = 1000, message = "La descripción debe tener entre 2 y 1000 caracteres")
    private String description;
}
