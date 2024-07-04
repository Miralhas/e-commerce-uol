package github.com.miralhas.ecommerce_uol.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private BigDecimal totalValue;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private List<OrderItemDTO> items;
    private String status;
}
