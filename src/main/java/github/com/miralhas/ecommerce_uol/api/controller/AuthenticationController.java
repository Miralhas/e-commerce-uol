package github.com.miralhas.ecommerce_uol.api.controller;

import github.com.miralhas.ecommerce_uol.api.dto.LoginDTO;
import github.com.miralhas.ecommerce_uol.api.dto.UserDTO;
import github.com.miralhas.ecommerce_uol.api.dto.input.CreateUserInput;
import github.com.miralhas.ecommerce_uol.api.dto.input.LoginInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.UserMapper;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.UserUnmapper;
import github.com.miralhas.ecommerce_uol.domain.model.User;
import github.com.miralhas.ecommerce_uol.domain.service.AuthenticationService;
import github.com.miralhas.ecommerce_uol.domain.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserUnmapper userUnmapper;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody @Valid CreateUserInput createUserInput) {
        User user = userUnmapper.toDomainObject(createUserInput);
        user = authenticationService.create(user);
        return userMapper.toModel(user);
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginDTO login(@RequestBody @Valid LoginInput loginInput) {
        var jwt = authenticationService.authenticate(loginInput);
        return new LoginDTO(jwt.getTokenValue(), TokenService.TOKEN_EXPIRATION_TIME);
    }

}