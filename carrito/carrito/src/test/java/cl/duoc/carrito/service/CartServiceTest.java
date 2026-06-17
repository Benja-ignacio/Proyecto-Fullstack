package cl.duoc.carrito.service;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import cl.duoc.carrito.dto.CartResponseDTO;
import cl.duoc.carrito.mappers.CartMapper;
import cl.duoc.carrito.model.Cart;
import cl.duoc.carrito.repository.CartItemRepository;
import cl.duoc.carrito.repository.CartRepository;

public class CartServiceTest {
    @Test
        void testGetCart() {
        CartRepository cartRepository = Mockito.mock(CartRepository.class);
        CartItemRepository cartItemRepository = Mockito.mock(CartItemRepository.class);
        CartMapper mapper = Mockito.mock(CartMapper.class); // ← faltaba este

        CartService cartService = new CartService(cartRepository, cartItemRepository, mapper);

        Cart cart = new Cart(1L, 1L);
        Mockito.when(cartRepository.findByUserId(1L)).thenReturn(Optional.of(cart));

        CartResponseDTO result = cartService.getCart(1L);

        Mockito.verify(cartRepository).findByUserId(1L);
    }
}
