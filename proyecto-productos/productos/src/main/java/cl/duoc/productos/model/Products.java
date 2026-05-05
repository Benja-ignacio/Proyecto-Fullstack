package cl.duoc.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

    private Long sku;
    private String nameProduct;
    private String typeProduct;
    private Integer priceProduct;
    private String descProduct;
}
