package github.com.miralhas.ecommerce_uol.domain.service;

import github.com.miralhas.ecommerce_uol.api.dto.input.OrderInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.OrderUnmapper;
import github.com.miralhas.ecommerce_uol.domain.exception.InactiveProductException;
import github.com.miralhas.ecommerce_uol.domain.exception.OrderNotFoundException;
import github.com.miralhas.ecommerce_uol.domain.model.Product;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import github.com.miralhas.ecommerce_uol.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderUnmapper orderUnmapper;


    public SalesOrder findByIdOrException(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }


    @Transactional
    public SalesOrder create(SalesOrder salesOrder) {
        validateOrderItems(salesOrder);
        salesOrder.calculateTotalPrice();
        return orderRepository.save(salesOrder);
    }


    @Transactional
    public SalesOrder update(Long id, OrderInput orderInput) {
        SalesOrder order = findByIdOrException(id);
        orderUnmapper.copyToDomainObject(orderInput, order);
        validateOrderItems(order);
        order.calculateTotalPrice();
        return orderRepository.save(order);
    }


    @Transactional
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }


    @Transactional
    public void confirmOrder(Long id) {
        findByIdOrException(id).confirm();
    }


    @Transactional
    public void cancelOrder(Long id) {
        findByIdOrException(id).cancel();
    }


    private void validateOrderItems(SalesOrder salesOrder) {
        salesOrder.getItems().forEach(item -> {
            Product product = productService.findByIdOrException(item.getProduct().getId());
            if (product.isInactive()) throw new InactiveProductException(product.getId());
            product.subtractStock(item.getQuantity());
            item.setSalesOrder(salesOrder);
            item.setProduct(product);
        });
    }
}
