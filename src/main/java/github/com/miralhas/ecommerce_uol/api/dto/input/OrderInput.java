package github.com.miralhas.ecommerce_uol.api.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInput {

    @Valid
    @NotNull
    @Size(min = 1)
    private List<OrderItemInput> items;
}
