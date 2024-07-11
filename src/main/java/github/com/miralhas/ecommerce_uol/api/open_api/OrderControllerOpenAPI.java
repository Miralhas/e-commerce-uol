package github.com.miralhas.ecommerce_uol.api.open_api;

import github.com.miralhas.ecommerce_uol.api.dto.OrderDTO;
import github.com.miralhas.ecommerce_uol.api.dto.PageDTO;
import github.com.miralhas.ecommerce_uol.api.dto.filter.OrderFilter;
import github.com.miralhas.ecommerce_uol.api.dto.input.OrderInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Orders")
public interface OrderControllerOpenAPI {

    @Operation(summary = "Get a list of all orders")
    ResponseEntity<PageDTO> getAllOrders(@PageableDefault(size = 2) Pageable pageable, OrderFilter filter);

    @Operation(summary = "Get a single order")
    OrderDTO getOrderById(@PathVariable Long id);

    @Operation(summary = "Create a order")
    OrderDTO createOrder(@RequestBody @Valid OrderInput orderInput);

    @Operation(summary = "Create a order")
    OrderDTO updateOrder(@PathVariable Long id, @RequestBody @Valid OrderInput orderInput);

    @Operation(summary = "Delete a order")
    void deleteOrder(@PathVariable Long id);

    @Operation(summary = "Confirm a order")
    void confirmOrder(@PathVariable Long id);

    @Operation(summary = "Cancel a order")
    void cancelOrder(@PathVariable Long id);
}
