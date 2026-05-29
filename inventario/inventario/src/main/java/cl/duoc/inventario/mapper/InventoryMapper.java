package cl.duoc.inventario.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.inventario.dto.InventoryResponseDTO;
import cl.duoc.inventario.model.Inventory;

@Component
public class InventoryMapper {

    // entity a dto
    public InventoryResponseDTO entityToInventoryResponseDTO(Inventory inventory) {
        return  InventoryResponseDTO.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .totalQuantity(inventory.getTotalQuantity())
                .availableQuantity(inventory.getAvailableQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .build();
    }
}
