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


        // Na hora de fazer o mapeamento do input para o orderItem, o modelmapper estava passando o id do produto para
        // o orderItem, por isso foi necessário especificar para que ele pule o mapeamento do ID.
        modelMapper.createTypeMap(OrderItemInput.class, OrderItem.class)
                .addMappings(mapper -> mapper.skip(OrderItem::setId));

        return modelMapper;
    }
}
