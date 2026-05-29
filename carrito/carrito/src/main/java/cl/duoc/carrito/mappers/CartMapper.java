package cl.duoc.carrito.mappers;

import java.util.List;

import cl.duoc.carrito.dto.CartItemResponseDTO;
import cl.duoc.carrito.dto.CartResponseDTO;
import cl.duoc.carrito.model.Cart;
import cl.duoc.carrito.model.CartItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartMapper {

    public CartItemResponseDTO entityToDTO(CartItem cartItem) {
        return CartItemResponseDTO.builder()
        .id(cartItem.getId())
        .cartId(cartItem.getCartId())
        .productId(cartItem.getProductId())
        .productName(cartItem.getProductName())
        .quantity(cartItem.getQuantity())
        .price(cartItem.getPrice())
        .build();
    }

    public CartResponseDTO CartWithItemsDTO(Cart cart, List<CartItem> items) {
        List<CartItemResponseDTO> itemsDTO = items.stream()
                                         .map(this::entityToDTO)
                                         .toList();

        return CartResponseDTO.builder()
        .id(cart.getId())
        .userId(cart.getUserId())
        .items(itemsDTO)
        .build();
    }
}
