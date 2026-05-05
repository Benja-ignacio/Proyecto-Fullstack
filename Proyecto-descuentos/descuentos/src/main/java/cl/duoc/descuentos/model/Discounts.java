package cl.duoc.descuentos.model;

import java.time.LocalDateTime;

public class Discounts {

    private Long id;
    private String code; //cupon: GAMER123
    private String description;
    private Double value; //VALOR DE DSCTO % O MONTO FIJO $
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active; //ACTIVO SI/NO

}
