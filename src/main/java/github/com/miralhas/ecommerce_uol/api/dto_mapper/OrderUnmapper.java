package github.com.miralhas.ecommerce_uol.api.dto_mapper;

import github.com.miralhas.ecommerce_uol.api.dto.input.OrderInput;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderUnmapper {

    private final ModelMapper modelMapper;

    public SalesOrder toDomainObject(OrderInput orderInput) {
        return modelMapper.map(orderInput, SalesOrder.class);
    }

    public void copyToDomainObject(OrderInput orderInput, SalesOrder salesOrder) {
        modelMapper.map(orderInput, salesOrder);
    }
}
