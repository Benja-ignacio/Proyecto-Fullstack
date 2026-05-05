package cl.duoc.carrito.service;

import cl.duoc.carrito.model.Cart;
import cl.duoc.carrito.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;

    // Add product to cart
    public Cart save(Cart cart) {
        cart.setTotal(cart.getQuantity() * cart.getPrice());
        return repository.save(cart);
    }

    // Get all cart products
    public List<Cart> findAll() {
        return repository.findAll();
    }

    // Delete product from cart
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Update quantity
    public Cart updateQuantity(Long id, Integer quantity) {
        Cart cart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        cart.setQuantity(quantity);
        cart.setTotal(cart.getQuantity() * cart.getPrice());

        return repository.save(cart);
    }

    // Calculate total cart
    public Double getTotalCart() {
        List<Cart> products = repository.findAll();

        return products.stream()
                .mapToDouble(Cart::getTotal)
                .sum();
    }
}