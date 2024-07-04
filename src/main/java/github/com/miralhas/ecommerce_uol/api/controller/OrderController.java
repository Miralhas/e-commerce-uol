package github.com.miralhas.ecommerce_uol.api.controller;

import github.com.miralhas.ecommerce_uol.api.dto.OrderDTO;
import github.com.miralhas.ecommerce_uol.api.dto.OrderSummaryDTO;
import github.com.miralhas.ecommerce_uol.api.dto.input.OrderInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.OrderMapper;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.OrderUnmapper;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import github.com.miralhas.ecommerce_uol.domain.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderUnmapper orderUnmapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderSummaryDTO> getAllOrders() {
        List<SalesOrder> salesOrders = orderService.findAll();
        return orderMapper.toSummaryCollectionModel(salesOrders);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO getOrderById(@PathVariable Long id) {
        SalesOrder salesOrder = orderService.findByIdOrException(id);
        return orderMapper.toModel(salesOrder);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO createOrder(@RequestBody @Valid OrderInput orderInput) {
        SalesOrder salesOrder = orderUnmapper.toDomainObject(orderInput);
        salesOrder = orderService.create(salesOrder);
        return orderMapper.toModel(salesOrder);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTO updateOrder(@PathVariable Long id, @RequestBody @Valid OrderInput orderInput) {
        SalesOrder order = orderService.update(id, orderInput);
        return orderMapper.toModel(order);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }


    @PutMapping("/{id}/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void confirmOrder(@PathVariable Long id) {
        orderService.confirmOrder(id);
    }


    @PutMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }

}
