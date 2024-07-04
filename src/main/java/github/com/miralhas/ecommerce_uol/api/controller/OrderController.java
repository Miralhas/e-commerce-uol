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


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public OrderDTO createOrder(@RequestBody @Valid OrderInput orderInput) {
//        SalesOrder salesOrder = orderUnmapper.toDomainObject(orderInput);
//        salesOrder = orderService.create(salesOrder);
//        return orderMapper.toModel(salesOrder);
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalesOrder createOrder(@RequestBody @Valid OrderInput orderInput) {
        SalesOrder salesOrder = orderUnmapper.toDomainObject(orderInput);
        SalesOrder savedOrder = orderService.create(salesOrder);
        System.out.println(savedOrder);
        return savedOrder;
    }

}
