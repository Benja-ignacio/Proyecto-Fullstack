package cl.duoc.productos.mapper;

import org.springframework.stereotype.Component;

import cl.duoc.productos.dto.ProductResponseDTO;
import cl.duoc.productos.model.Product;

@Component
public class ProductMapper {

    public ProductResponseDTO entityToProductResponseDTO(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .brand(product.getBrand())
                .type(product.getType())
                .price(product.getPrice())
                .description(product.getDescription())
                .status(product.getStatus())
                .build();
    }
}
