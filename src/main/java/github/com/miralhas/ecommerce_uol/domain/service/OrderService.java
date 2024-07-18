package github.com.miralhas.ecommerce_uol.domain.service;

import github.com.miralhas.ecommerce_uol.api.dto.input.OrderInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.OrderUnmapper;
import github.com.miralhas.ecommerce_uol.domain.exception.BusinessException;
import github.com.miralhas.ecommerce_uol.domain.exception.InactiveProductException;
import github.com.miralhas.ecommerce_uol.domain.exception.OrderNotFoundException;
import github.com.miralhas.ecommerce_uol.domain.model.Product;
import github.com.miralhas.ecommerce_uol.domain.model.Role;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import github.com.miralhas.ecommerce_uol.domain.model.User;
import github.com.miralhas.ecommerce_uol.domain.repository.OrderRepository;
import github.com.miralhas.ecommerce_uol.domain.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderUnmapper orderUnmapper;
    private final AuthenticationService authenticationService;
    private final RoleRepository roleRepository;
    private final MessageSource messageSource;

    public SalesOrder findByIdOrException(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }


    @Transactional
    public SalesOrder create(SalesOrder salesOrder, JwtAuthenticationToken authToken) {
        validateOrderItems(salesOrder);
        salesOrder.calculateTotalPrice();
        var authUser = authenticationService.findUserByEmailOrException(authToken.getName());
        salesOrder.setUser(authUser);
        return orderRepository.save(salesOrder);
    }


    @Transactional
    public SalesOrder update(Long id, OrderInput orderInput, JwtAuthenticationToken authToken) {
        SalesOrder order = findByIdOrException(id);
        User user = authenticationService.findUserByEmailOrException(authToken.getName());
        validateOrderOwner(user, order);
        orderUnmapper.copyToDomainObject(orderInput, order);
        validateOrderItems(order);
        order.calculateTotalPrice();
        return orderRepository.save(order);
    }


    private void validateOrderOwner(User user, SalesOrder order) {
        Role adminRole = roleRepository.findRoleByName(Role.Value.ADMIN);
        if (user.getRoles().contains(adminRole) || Objects.equals(user, order.getUser())) return;
        throw new AccessDeniedException(messageSource.getMessage(
                "AbstractAccessDecisionManager.accessDenied", new Object[]{}, LocaleContextHolder.getLocale()
        ));
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
