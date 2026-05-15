package cl.duoc.inventario.dto;

import lombok.Builder;

@Builder
public class InventoryResponseDTO {
    private Long id;
    private Long productId;
    private Integer totalQuantity;
    private Integer availableQuantity;
    private Integer reservedQuantity;
}
