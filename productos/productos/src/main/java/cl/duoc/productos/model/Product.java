package cl.duoc.productos.model;

import java.math.BigDecimal;

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

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    @Column(name = "sku",nullable = false, unique = true)
    @GeneratedValue()
    private String sku; // generar automaticamente 

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private Status Status;  
}
