package cl.duoc.productos.model;

import java.math.BigDecimal;

import cl.duoc.productos.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private String sku; // generar automaticamente 

    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "La marca no puede estar vacia")
    @Column(name = "brand", nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status Status;  
}
