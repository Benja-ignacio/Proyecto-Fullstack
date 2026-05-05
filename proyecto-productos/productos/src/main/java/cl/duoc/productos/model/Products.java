package cl.duoc.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Products {

    private Long sku;
    private String name;
    private String type;
    private Integer price;
    private String description;
}
