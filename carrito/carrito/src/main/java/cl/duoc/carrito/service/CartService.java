package cl.duoc.carrito.service;

import cl.duoc.carrito.dto.CartItemDTO;
import cl.duoc.carrito.dto.CartItemResponseDTO;
import cl.duoc.carrito.dto.CartResponseDTO;
import cl.duoc.carrito.mappers.CartMapper;
import cl.duoc.carrito.model.Cart;
import cl.duoc.carrito.model.CartItem;
import cl.duoc.carrito.repository.CartItemRepository;
import cl.duoc.carrito.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class CartService {

    private static final Logger logger =
        LoggerFactory.getLogger(CartService.class);  

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper mapper;

    public CartItemResponseDTO addProduct(Long userId, CartItemDTO dto) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        CartItem item = new CartItem(
                null,
                cart.getId(),
                dto.getProductId(),
                dto.getProductName(),
                dto.getQuantity(),
                dto.getPrice()
        );

        cartItemRepository.save(item);

        return mapper.entityToDTO(item);
    }

    public CartResponseDTO getCart(Long userId) {

        Cart cart= cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        return mapper.CartWithItemsDTO(cart, items);
    }

    public void deleteProduct(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public CartItemResponseDTO updateQuantity(Long itemId, Integer quantity) {

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        item.setQuantity(quantity);

        cartItemRepository.save(item);
        
        return mapper.entityToDTO(item);
    }

    public BigDecimal calculateTotal(Long cartID) {

        List<CartItem> items = cartItemRepository.findByCartId(cartID);

        return items.stream()
                .map(item ->
                        item.getPrice().multiply(
                                BigDecimal.valueOf(item.getQuantity())
                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}