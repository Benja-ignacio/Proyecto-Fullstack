package cl.duoc.carrito.service;

import cl.duoc.carrito.dto.CartItemDTO;
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

    public CartItem addProduct(Long userId, CartItemDTO dto) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(
                        new Cart(null, userId)
                ));

        CartItem item = new CartItem(
                null,
                cart.getId(),
                dto.getProductId(),
                dto.getProductName(),
                dto.getQuantity(),
                dto.getPrice()
        );

        return cartItemRepository.save(item);
    }

    public List<CartItem> getCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        return cartItemRepository.findByCartId(cart.getId());
    }

    public void deleteProduct(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public CartItem updateQuantity(Long itemId, Integer quantity) {

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        item.setQuantity(quantity);

        return cartItemRepository.save(item);
    }

    public BigDecimal calculateTotal(Long userId) {

        List<CartItem> items = getCart(userId);

        return items.stream()
                .map(item ->
                        item.getPrice().multiply(
                                BigDecimal.valueOf(item.getQuantity())
                        )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}