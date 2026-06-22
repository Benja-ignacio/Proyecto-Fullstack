package cl.duoc.inventario.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.inventario.client.ProductWebClient;
import cl.duoc.inventario.dto.InventoryResponseDTO;
import cl.duoc.inventario.mapper.InventoryMapper;
import cl.duoc.inventario.model.Inventory;
import cl.duoc.inventario.repository.InventoryRepository;

public class InventoryServiceTest {

    @Test
    void testGetAllInventory() {
        InventoryRepository inventoryRepository = Mockito.mock(InventoryRepository.class);
        InventoryMapper mapper = Mockito.mock(InventoryMapper.class);
        ProductWebClient productWebClient = Mockito.mock(ProductWebClient.class);

        InventoryService inventoryService = new InventoryService(
                inventoryRepository,
                mapper,
                productWebClient
        );

        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setProductId(1L);
        inventory.setTotalQuantity(30);
        inventory.setReservedQuantity(10);
        inventory.setAvailableQuantity(20);

        InventoryResponseDTO responseDTO = Mockito.mock(InventoryResponseDTO.class);

        Mockito.when(inventoryRepository.findAll()).thenReturn(List.of(inventory));
        Mockito.when(mapper.entityToInventoryResponseDTO(inventory)).thenReturn(responseDTO);

        List<InventoryResponseDTO> result = inventoryService.findAll();

        assertThat(result).hasSize(1);
    }
}