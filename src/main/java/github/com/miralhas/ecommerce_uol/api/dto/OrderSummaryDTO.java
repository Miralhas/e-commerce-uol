package github.com.miralhas.ecommerce_uol.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderSummaryDTO {
    private Long id;
    private BigDecimal totalValue;
    private OffsetDateTime createdAt;
}
