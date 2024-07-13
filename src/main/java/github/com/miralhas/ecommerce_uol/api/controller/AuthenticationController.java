package github.com.miralhas.ecommerce_uol.api.controller;

import github.com.miralhas.ecommerce_uol.api.dto.UserDTO;
import github.com.miralhas.ecommerce_uol.api.dto.input.CreateUserInput;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.UserMapper;
import github.com.miralhas.ecommerce_uol.api.dto_mapper.UserUnmapper;
import github.com.miralhas.ecommerce_uol.domain.model.User;
import github.com.miralhas.ecommerce_uol.domain.security.SecurityUser;
import github.com.miralhas.ecommerce_uol.domain.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    public UserDTO loginUser(UsernamePasswordAuthenticationToken authenticationToken) {
        var authUser = (SecurityUser) authenticationToken.getPrincipal();
        return userMapper.toModel(authUser.getUser());
    }

}