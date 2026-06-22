package cl.duoc.descuentos.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.descuentos.dto.DiscountResponseDTO;
import cl.duoc.descuentos.enums.ProductType;
import cl.duoc.descuentos.enums.Type;
import cl.duoc.descuentos.mapper.DiscountMapper;
import cl.duoc.descuentos.model.Discount;
import cl.duoc.descuentos.repository.DiscountRepository;
import cl.duoc.descuentos.repository.DiscountUsageRepository;

public class DiscountServiceTest {
    @Test
    void testGetDiscount() {
        DiscountRepository discountRepository = Mockito.mock(DiscountRepository.class);
        DiscountUsageRepository discountUsageRepository = Mockito.mock(DiscountUsageRepository.class);
        DiscountMapper mapper = Mockito.mock(DiscountMapper.class); // ← faltaba este

        DiscountService discountService = new DiscountService(discountRepository, discountUsageRepository, mapper);

        Discount discount = new Discount(1L, "abc123", "Descuento en teclados", Type.PERCENTAGE, ProductType.KEYBOARD, BigDecimal.valueOf(15), BigDecimal.valueOf(20000), BigDecimal.valueOf(150000), null, 0, null, null, null, true);
        Mockito.when(discountRepository.findById(1L)).thenReturn(Optional.of(discount));

        DiscountResponseDTO result = discountService.getById(1L);

        Mockito.verify(discountRepository).findById(1L);}
    

}
