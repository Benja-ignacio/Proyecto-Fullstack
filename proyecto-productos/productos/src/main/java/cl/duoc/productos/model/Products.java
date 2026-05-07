package cl.duoc.productos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {
    private Long id; // pk
    private String sku; // generar automaticamente 
    private String name;
    private String type;
    private Integer price;
    private String description;
    private String Status; // ENUM 
}
