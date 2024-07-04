package github.com.miralhas.ecommerce_uol.api.dto_mapper;

import github.com.miralhas.ecommerce_uol.api.dto.ProductDTO;
import github.com.miralhas.ecommerce_uol.api.dto.ProductSummaryDTO;
import github.com.miralhas.ecommerce_uol.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final ModelMapper modelMapper;

    public ProductDTO toModel(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductSummaryDTO toSummaryModel(Product product) {
        return modelMapper.map(product, ProductSummaryDTO.class);
    }

    public List<ProductSummaryDTO> toSummaryCollectionModel(List<Product> products) {
        return products.stream().map(this::toSummaryModel).toList();
    }
}
