package cl.duoc.inventario.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 🟢 NUEVO

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

    // crear inventario
    @Transactional //seguridad para operacion, si se ejecuta se guarda todo o no se guarda nada
    public InventoryResponseDTO create(InventoryRequestDTO request) {
        Integer reserved = request.getReservedQuantity();
        Integer total = request.getTotalQuantity();
        
        if (reserved == null || total == null) {
            throw new InvalidRequestException("Valores no pueden ser nulos");
        }

        if (reserved > total) {
            throw new InvalidRequestException("No puedes tener mas reservas que la cantidad total");
        } 
            // calculo automatico
        int available = total - reserved;

        Inventory inventory = new Inventory();
        inventory.setProductId(request.getProductId());
        inventory.setTotalQuantity(total);
        inventory.setReservedQuantity(reserved);
        inventory.setAvailableQuantity(available); // asigna disponibilidad 

        inventoryRepository.save(inventory); //guarda en la bdd y returna en dto
        return mapper.entityToInventoryResponseDTO(inventory);
    }

    // buscar por id
    public InventoryResponseDTO getById(Long id) {
        Inventory dto = inventoryRepository.findById(id)
                        .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));
        return mapper.entityToInventoryResponseDTO(dto);
    }

    // buscar por productId
    public InventoryResponseDTO getByProductId(long id) {
        Inventory dto = inventoryRepository.findByProductId(id)
                        .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));
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
    @Transactional
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

        //  RE-CALCULAR EL DISPONIBLE
        int available = total - reserved;

        inventory.setTotalQuantity(total);
        inventory.setReservedQuantity(reserved);
        inventory.setAvailableQuantity(available); // 🟢 Sincronizado con la lógica SQL
        
        inventoryRepository.save(inventory);
        return mapper.entityToInventoryResponseDTO(inventory);
    }

    // eliminar inventario
    @Transactional
    public void delete(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryNotFoundException("Inventario no encontrado"));
        inventoryRepository.delete(inventory);
    }

    
    //Valida si hay stock disponible para un pedido.
     
    public boolean checkAvailableStock(Long productId, Integer quantity) {
        return inventoryRepository.findByProductId(productId)
                .map(inv -> inv.getAvailableQuantity() >= quantity)
                .orElse(false);
    }

    
    //  Mueve stock de Disponible a Reservado cuando se genera la orden.
    @Transactional
    public boolean reserveStock(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Producto no registrado en inventario"));

        if (inventory.getAvailableQuantity() >= quantity) {
            inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
            inventory.setReservedQuantity(inventory.getReservedQuantity() + quantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }

    
    //Descuenta definitivamente el stock del Total cuando el pago fue exitoso.
     
    @Transactional
    public boolean confirmStockSale(Long productId, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Producto no registrado en inventario"));

        if (inventory.getReservedQuantity() >= quantity) {
            inventory.setReservedQuantity(inventory.getReservedQuantity() - quantity);
            inventory.setTotalQuantity(inventory.getTotalQuantity() - quantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }
}