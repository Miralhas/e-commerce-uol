package github.com.miralhas.ecommerce_uol.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSummaryDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String status;
}
