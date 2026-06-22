package cl.duoc.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.inventario.client.ProductWebClient;
import cl.duoc.inventario.dto.InventoryRequestDTO;
import cl.duoc.inventario.dto.InventoryResponseDTO;
import cl.duoc.inventario.dto.InventoryUpdateRequestDTO;
import cl.duoc.inventario.exception.custom.InvalidRequestException;
import cl.duoc.inventario.exception.custom.InventoryNotFoundException;
import cl.duoc.inventario.mapper.InventoryMapper;
import cl.duoc.inventario.model.Inventory;
import cl.duoc.inventario.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper mapper;
    private final ProductWebClient productWebClient; // Cambiado para inyectar tu nuevo cliente personalizado

    // crear inventario
    public InventoryResponseDTO create(InventoryRequestDTO request) {
        // 1. Validar que venga el ID antes de mandarlo a la red
        if (request.getProductId() == null) {
            throw new InvalidRequestException("El ID del producto es obligatorio");
        }
        
        // 2. Comprobar la existencia del producto consumiendo tu componente externo
        productWebClient.productExists(request.getProductId());

        // 3. Validaciones de cantidades existentes
        Integer reserved = request.getReservedQuantity();
        Integer total = request.getTotalQuantity();
        
        if (reserved == null || total == null) {
            throw new InvalidRequestException("Valores no pueden ser nulos");
        }

        if (reserved > total) {
            throw new InvalidRequestException("No puedes tener mas reservas que la cantidad total");
        } 

        Inventory inventory = new Inventory();
        inventory.setProductId(request.getProductId());
        inventory.setTotalQuantity(request.getTotalQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());

        inventoryRepository.save(inventory);

        return mapper.entityToInventoryResponseDTO(inventory);
    }

    // buscar por id
    public InventoryResponseDTO getById(Long id) {
        Inventory dto = inventoryRepository.findById(id)
                        .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));
<<<<<<< HEAD
=======

>>>>>>> eliascarcamo
        return mapper.entityToInventoryResponseDTO(dto);
    }

    // buscar por productId
    public InventoryResponseDTO getByProductId(long id) {
        Inventory dto = inventoryRepository.findByProductId(id)
                        .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));
<<<<<<< HEAD
=======

>>>>>>> eliascarcamo
        return mapper.entityToInventoryResponseDTO(dto);
    }
    
    // buscar todos los inventarios
    public List<InventoryResponseDTO> findAll() {
        List<Inventory> list = inventoryRepository.findAll();
        return list.stream()
                .map(mapper::entityToInventoryResponseDTO)
                .toList();
    }

    // actualizar inventario
    public InventoryResponseDTO update (Long id, InventoryUpdateRequestDTO request) {
        Inventory inventory = inventoryRepository.findById(id)
                        .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));

        Integer total = request.getTotalQuantity();
        Integer reserved = request.getReservedQuantity();

        if (reserved == null || total == null) {
            throw new InvalidRequestException("Valores no pueden ser nulos");
        }

        if (reserved > total) {
            throw new InvalidRequestException("No puedes tener mas reservas que la cantidad total");
        } 

        inventory.setTotalQuantity(request.getTotalQuantity());
        inventory.setReservedQuantity(request.getReservedQuantity());
        
        inventoryRepository.save(inventory);

        return mapper.entityToInventoryResponseDTO(inventory);
    }

    // eliminar inventario
    public void delete(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));

        inventoryRepository.delete(inventory);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> eliascarcamo
