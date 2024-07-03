package github.com.miralhas.ecommerce_uol.api.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductInput {
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Positive
    private Integer stock;

    @Positive
    private BigDecimal price;
}
