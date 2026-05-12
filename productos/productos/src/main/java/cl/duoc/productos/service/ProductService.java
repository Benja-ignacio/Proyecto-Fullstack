package cl.duoc.productos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cl.duoc.productos.dto.ProductRequestDTO;
import cl.duoc.productos.dto.ProductResponseDTO;
import cl.duoc.productos.dto.UpdateProductRequestDTO;
import cl.duoc.productos.enums.Status;
import cl.duoc.productos.exception.custom.ProductAlreadyExistsException;
import cl.duoc.productos.exception.custom.ProductNotFoundException;
import cl.duoc.productos.model.Product;
import cl.duoc.productos.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO createProduct(ProductRequestDTO request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new ProductAlreadyExistsException("El producto ya esta registrado");
        }

        Product product = new Product();
        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setType(request.getType());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStatus(Status.ACTIVE);

        Product saved = productRepository.save(product);

        return mapToDTO(saved);
    }


    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado")); // exception ProductNotFoundException

        return mapToDTO(product);
    }


    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO updateProduct(Long id, UpdateProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado")); // exception ProductNotFoundException

        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setType(request.getType());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        Product updated = productRepository.save(product);

        return mapToDTO(updated);
    }


    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));

        product.setStatus(Status.DISCONTINUED);

        productRepository.save(product);
    }

    public ProductResponseDTO changeStatus(Long id, Status status) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto no encontrado"));

        product.setStatus(status);
        Product updated = productRepository.save(product);
        return mapToDTO(updated);
    }

    private ProductResponseDTO mapToDTO(Product product) {
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