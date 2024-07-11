package github.com.miralhas.ecommerce_uol.api.dto_mapper;

import github.com.miralhas.ecommerce_uol.api.dto.OrderDTO;
import github.com.miralhas.ecommerce_uol.api.dto.OrderSummaryDTO;
import github.com.miralhas.ecommerce_uol.domain.model.SalesOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper modelMapper;

    public OrderDTO toModel(SalesOrder salesOrder) {
        return modelMapper.map(salesOrder, OrderDTO.class);
    }

    public OrderSummaryDTO toSummaryModel(SalesOrder salesOrder) {
        return modelMapper.map(salesOrder, OrderSummaryDTO.class);
    }

    public List<OrderSummaryDTO> toSummaryCollectionModel(List<SalesOrder> salesOrders) {
        return salesOrders.stream().map(this::toSummaryModel).toList();
    }
}
