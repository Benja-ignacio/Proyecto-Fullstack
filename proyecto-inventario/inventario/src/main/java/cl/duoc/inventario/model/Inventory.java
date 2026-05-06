package cl.duoc.inventario.model;

public class Inventory {
    private Long id;
    private Long productId; // referencia a product service

    private Integer totalQuantity;
    private Integer availableQuantity;
    private Integer reservedQuantity;
}
