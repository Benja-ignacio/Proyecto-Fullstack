package cl.duoc.productos.model;

import cl.duoc.productos.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Products {
    private Long id; // pk

    @Column(name = "sku")
    private String sku; // generar automaticamente 

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Type type;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Status Status;  
}
