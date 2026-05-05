package cl.duoc.carrito.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El producto es obligatorio")
    private String product;

    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer quantity;

    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double price;

    private Double total;
}
