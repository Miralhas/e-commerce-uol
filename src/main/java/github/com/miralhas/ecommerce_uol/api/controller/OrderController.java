package github.com.miralhas.ecommerce_uol.api.controller;

import github.com.miralhas.ecommerce_uol.api.dto.OrderDTO;
import github.com.miralhas.ecommerce_uol.api.dto.OrderSummaryDTO;
import github.com.miralhas.ecommerce_uol.api.dto.PageDTO;
import github.com.miralhas.ecommerce_uol.api.dto.filter.OrderFilter;
import github.com.miralhas.ecommerce_uol.api.dto.input.OrderInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.OrderMapper;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.OrderUnmapper;
import github.com.miralhas.ecommerce_uol.config.model_mapper.PagesMapper;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import github.com.miralhas.ecommerce_uol.domain.repository.OrderRepository;
import github.com.miralhas.ecommerce_uol.domain.service.OrderService;
import github.com.miralhas.ecommerce_uol.infrastructure.repository.spec.OrderSpec;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderUnmapper orderUnmapper;
    private final OrderRepository orderRepository;
    private final PagesMapper pagesMapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PageDTO> getAllOrders(@PageableDefault(size = 2) Pageable pageable, OrderFilter filter) {
        Page<SalesOrder> pageOrder = orderRepository.findAll(OrderSpec.withFilter(filter), pageable);
        List<OrderSummaryDTO> ordersSummaryDTO = orderMapper.toSummaryCollectionModel(pageOrder.getContent());
        Page<OrderSummaryDTO> pageOrderSummary = new PageImpl<>(ordersSummaryDTO, pageable, pageOrder.getTotalElements());
        PageDTO pageDTO = pagesMapper.toModel(pageOrderSummary);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(pageDTO);
    }


    @GetMapping("/monthly")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderSummaryDTO>> getAllOrdersByMonth(@RequestParam(name = "num", required = false) Integer num) {
        if (num == null) num = OffsetDateTime.now().getMonthValue();
        List<SalesOrder> salesOrders = orderRepository.findAllOrdersByMonth(num);
        List<OrderSummaryDTO> ordersSummaryDTO = orderMapper.toSummaryCollectionModel(salesOrders);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(ordersSummaryDTO);
    }


    @GetMapping("/weekly")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<OrderSummaryDTO>> getAllWeeklyOrders() {
        List<SalesOrder> salesOrders = orderRepository.findAllWeeklyOrders();
        List<OrderSummaryDTO> ordersSummaryDTO = orderMapper.toSummaryCollectionModel(salesOrders);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(ordersSummaryDTO);
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
