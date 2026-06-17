package cl.duoc.inventario.dto;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryUpdateRequestDTO {
    @PositiveOrZero(message = "La cantidad disponible no puede ser menor a 0")
    private Integer totalQuantity;

    @PositiveOrZero(message = "La cantidad reservada no puede ser menor a 0")
    private Integer reservedQuantity;
}
