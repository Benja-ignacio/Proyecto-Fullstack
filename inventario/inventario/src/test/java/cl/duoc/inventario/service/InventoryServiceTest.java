package cl.duoc.inventario.service;

// import java.util.List;

// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.web.reactive.function.client.WebClient;

// import cl.duoc.inventario.dto.InventoryResponseDTO;
// import cl.duoc.inventario.mapper.InventoryMapper;
// import cl.duoc.inventario.model.Inventory;
// import cl.duoc.inventario.repository.InventoryRepository;

// import static org.assertj.core.api.Assertions.assertThat;


// public class InventoryServiceTest {
//     @Test
//     void testGetAllInventory() {
//         InventoryRepository inventoryRepository = Mockito.mock(InventoryRepository.class);
//         InventoryMapper mapper = Mockito.mock(InventoryMapper.class); // ← faltaba este
//         WebClient productWebClient= Mockito.mock(WebClient.class);
//         InventoryService inventoryService = new InventoryService(inventoryRepository, mapper, productWebClient);



//         Inventory inventory = new Inventory(1L, 1L, 30, 10);
//         Mockito.when(inventoryRepository.findAll()).thenReturn(List.of(inventory));

//         List<InventoryResponseDTO> result = inventoryService.findAll();

//         assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
//     }
