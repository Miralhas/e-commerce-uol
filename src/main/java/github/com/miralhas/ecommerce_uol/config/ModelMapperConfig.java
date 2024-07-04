package github.com.miralhas.ecommerce_uol.config;

import github.com.miralhas.ecommerce_uol.api.dto.input.OrderItemInput;
import github.com.miralhas.ecommerce_uol.domain.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

//        modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
//                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        return modelMapper;
    }
}
