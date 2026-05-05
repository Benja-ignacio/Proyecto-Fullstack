package cl.duoc.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private String product;
    private Integer quantity;
    private Double price;
    private Double total;
}