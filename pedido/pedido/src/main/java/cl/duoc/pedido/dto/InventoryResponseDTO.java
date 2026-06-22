package cl.duoc.pedido.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponseDTO {

    private Long productId;

    private String productName;

    private Integer stockAvailable;

    private Boolean available;
}
