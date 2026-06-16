package cl.duoc.productos.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.productos.dto.ProductResponseDTO;
import cl.duoc.productos.enums.Status;
import cl.duoc.productos.enums.Type;
import cl.duoc.productos.mapper.ProductMapper;
import cl.duoc.productos.model.Product;
import cl.duoc.productos.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;


public class ProductServiceTest {
    @Test
        void testGetAllProductsDTO() {
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        ProductMapper mapper = Mockito.mock(ProductMapper.class); 
        ProductService productService = new ProductService(productRepository, mapper);

        Product product = new Product(1L, "GPU-3-ASUS","RTX 4070 Dual","ASUS",Type.GPU, BigDecimal.valueOf(749990.00),"Tarjeta gráfica NVIDIA RTX 4070", Status.COMING_SOON);
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductResponseDTO> result = productService.getAllProducts();

        assertThat(result).hasSize(1); // verifica que la lista resultante tenga un tamaño de 1
    }

}
