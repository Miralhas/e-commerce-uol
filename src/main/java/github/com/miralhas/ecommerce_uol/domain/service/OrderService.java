package github.com.miralhas.ecommerce_uol.domain.service;

import github.com.miralhas.ecommerce_uol.domain.exception.OrderNotFoundException;
import github.com.miralhas.ecommerce_uol.domain.model.Product;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import github.com.miralhas.ecommerce_uol.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public List<SalesOrder> findAll() {
        return orderRepository.findAll();
    }

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


    private void validateOrderItems(SalesOrder salesOrder) {
        salesOrder.getItems().forEach(item -> {
            Product product = productService.findByIdOrException(item.getProduct().getId());
            product.subtractStock(item.getQuantity());
            item.setSalesOrder(salesOrder);
            item.setProduct(product);
            item.setId(null);
        });
    }
}
