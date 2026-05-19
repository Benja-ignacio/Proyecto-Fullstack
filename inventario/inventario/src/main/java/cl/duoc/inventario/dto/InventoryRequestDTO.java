package cl.duoc.inventario.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDTO {


    @NotNull(message = "El productId es requerido")
    private Long ProductId;

    @NotNull(message = "totalQuantity no puede ser nulo")
    @PositiveOrZero(message = "La cantidad disponible no puede ser menor a 0")
    private Integer totalQuantity;

    @NotNull(message = "reservedQuantity no puede ser nulo")
    @PositiveOrZero(message = "La cantidad reservada no puede ser menor a 0")
    private Integer reservedQuantity;
    
}
