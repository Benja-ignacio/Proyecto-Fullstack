package cl.duoc.descuentos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.descuentos.dto.DiscountRequestDTO;
import cl.duoc.descuentos.dto.DiscountResponseDTO;
import cl.duoc.descuentos.dto.DiscountUsageResponseDTO;
import cl.duoc.descuentos.exception.custom.DiscountAlreadyActiveException;
import cl.duoc.descuentos.exception.custom.DiscountAlreadyExistsException;
import cl.duoc.descuentos.exception.custom.DiscountNotFoundException;
import cl.duoc.descuentos.exception.custom.InvalidDateException;
import cl.duoc.descuentos.model.Discount;
import cl.duoc.descuentos.model.DiscountUsage;
import cl.duoc.descuentos.repository.DiscountRepository;
import cl.duoc.descuentos.repository.DiscountUsageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountUsageRepository discountUsageRepository;

    // crear descuento

    public DiscountResponseDTO create(DiscountRequestDTO request) {

        if (discountRepository.existsByCode(request.getCode())) {
            throw new DiscountAlreadyExistsException("El codigo ya existe");
        }

        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new InvalidDateException("fechas invalidas");
        }

        Discount discount = new Discount();
        discount.setCode(request.getCode());
        discount.setDescription(request.getDescription());
        discount.setType(request.getType());
        discount.setProductType(request.getProductType());
        discount.setValue(request.getValue());
        discount.setMinPurchaseAmount(request.getMinPurchaseAmount());
        discount.setMaxDiscountAmount(request.getMaxDiscountAmount());
        discount.setUsageLimit(request.getUsageLimit());
        discount.setUsageLimitPerUser(request.getUsageLimitPerUser());
        discount.setStartDate(request.getStartDate());
        discount.setEndDate(request.getEndDate());

        discountRepository.save(discount);

        return mapToDTO(discount);
    }


    // activar/desactivar descuento 
    public DiscountResponseDTO toggleStatus(Long id, boolean active) {
        Discount discount = discountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("error")); // crear excepcion personalizada

        if (discount.isActive() == active) {
            throw new DiscountAlreadyActiveException("EL descuento ya esta activo");
        }
        discount.setActive(active);

        discountRepository.save(discount);

        return mapToDTO(discount);
    }

    
    // actualizar descuento
    // metodo patch, si viene null no se toca
    public DiscountResponseDTO update(Long id, DiscountRequestDTO request) {
        Discount discount = discountRepository.findById(id)
            .orElseThrow(() -> new DiscountNotFoundException("Descuento no encontrado")); // crear excepcion personalizada


        if (request.getStartDate() != null && request.getEndDate() != null) {
            if (request.getEndDate().isBefore(request.getStartDate())) {
                throw new InvalidDateException("fechas invalidas");
        }}


        if (request.getDescription() != null) {discount.setDescription(request.getDescription());} 
        if (request.getType() != null) {discount.setType(request.getType());}
        if (request.getProductType() != null) {discount.setProductType(request.getProductType());}
        if (request.getValue() != null) {discount.setValue(request.getValue());}
        if (request.getMinPurchaseAmount() != null) {discount.setMinPurchaseAmount(request.getMinPurchaseAmount());}
        if (request.getUsageLimit() != null) {discount.setUsageLimit(request.getUsageLimit());}
        if (request.getMaxDiscountAmount() != null) {discount.setMaxDiscountAmount(request.getMaxDiscountAmount());}
        if (request.getUsageLimitPerUser() != null) {discount.setUsageLimitPerUser(request.getUsageLimitPerUser());}
        if (request.getStartDate() != null) {discount.setStartDate(request.getStartDate());}
        if (request.getEndDate() != null) {discount.setEndDate(request.getEndDate());}

        discountRepository.save(discount);

        return mapToDTO(discount);
    }

    //eliminar descuento
    public void deleteDiscount(Long id) {

        Discount discount = discountRepository.findById(id)
                            .orElseThrow(() -> new DiscountNotFoundException("Descuento no encontrado")); // crear excepcion personalizada

        discountRepository.delete(discount);
    }

    // buscar descuento por id
    public DiscountResponseDTO getById(Long id) {

        Discount discount = discountRepository.findById(id)
                            .orElseThrow(() -> new DiscountNotFoundException("Descuento no encontrado"));

        return mapToDTO(discount);
    }

    // buscar descuentos de un usuario
    public List<DiscountUsageResponseDTO> getByUserId(Long userID) {
        List<DiscountUsage> discounts = discountUsageRepository.findByUserId(userID);

        return discounts.stream()
                        .map(this::mapToDTO)
                        .toList();
    }

    // entity to dto de discounts 
    public DiscountResponseDTO mapToDTO(Discount discount) {
        return DiscountResponseDTO.builder()
            .code(discount.getCode())
            .description(discount.getDescription())
            .type(discount.getType())
            .productType(discount.getProductType())
            .value(discount.getValue())
            .minPurchaseAmount(discount.getMinPurchaseAmount())
            .maxDiscountAmount(discount.getMaxDiscountAmount())
            .usageLimit(discount.getUsageLimit())
            .usageLimitPerUser(discount.getUsageLimitPerUser())
            .startDate(discount.getStartDate())
            .endDate(discount.getEndDate())
            .active(discount.isActive())
            .build();
    }

    // entity to dto de discountUsage
    public DiscountUsageResponseDTO mapToDTO(DiscountUsage discountUsage) {
        return DiscountUsageResponseDTO.builder()
            .id(discountUsage.getId())
            .discountId(discountUsage.getDiscountId())
            .userId(discountUsage.getUserId())
            .usedAt(discountUsage.getUsedAt())
            .build();
    }



}
