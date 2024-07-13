package github.com.miralhas.ecommerce_uol.api.dto_mapper;

import github.com.miralhas.ecommerce_uol.api.dto.input.CreateUserInput;
import github.com.miralhas.ecommerce_uol.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUnmapper {

    private final ModelMapper modelMapper;

    public User toDomainObject(CreateUserInput createUserInput) {
        return modelMapper.map(createUserInput, User.class);
    }

}