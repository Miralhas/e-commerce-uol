package github.com.miralhas.ecommerce_uol.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemDTO {
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal productPrice;
    private BigDecimal totalPrice;
}
